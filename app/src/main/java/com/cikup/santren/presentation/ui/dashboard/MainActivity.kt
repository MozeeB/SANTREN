package com.cikup.santren.presentation.ui.dashboard

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.cikup.santren.R
import com.cikup.santren.adapter.InformationAdapter
import com.cikup.santren.adapter.MenuAdapter
import com.cikup.santren.data.model.InformationModel
import com.cikup.santren.data.model.MenuModel
import com.cikup.santren.helper.Field
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.helper.Role
import com.cikup.santren.presentation.navigation.backToLogin
import com.cikup.santren.presentation.navigation.navigateToInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_fab_menu.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logoutBTN.setOnClickListener(this)
        liharSemuaTV.setOnClickListener(this)

        initUser()

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.liharSemuaTV ->{
                navigateToInformation(this)
            }
            R.id.logoutBTN ->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Logout")
                builder.setMessage("Apakah anda yakin ingin log out?")
                builder.setPositiveButton("Ya") { dialog, which ->
                    FirebaseAuth.getInstance().signOut()
                    backToLogin(this)
                }
                builder.setNegativeButton("Tidak") { dialog, which ->
                    dialog.dismiss()
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUser(){
        val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
        QueryRead.users
            .document(currentUserId)
            .get()
            .addOnCompleteListener {
                if (it.result != null){
                    val data = it.result!!
                    nameUserDashboardTV.text = "Halo ${data[Field.name]}"
                    when(data[Field.role]){
                        Role.guru ->{
                            speedDial.visibility = View.VISIBLE
                            speedDial.inflate(R.menu.menu_teacher)
                            speedDial.setOnActionSelectedListener { action ->
                                when (action?.id) {
                                    R.id.fabLaporanAbnsensiSantri -> {
                                        speedDial.close()
                                        return@setOnActionSelectedListener true
                                    }
                                    R.id.fabJadwalAcaraSantri -> {
                                        speedDial.close()
                                        return@setOnActionSelectedListener true

                                    }
                                    R.id.fabInformation -> {
                                        speedDial.close()
                                        return@setOnActionSelectedListener true

                                    }
                                }
                                false
                            }
                        }
                        Role.walsan ->{
                            speedDial.visibility = View.GONE

                        }
                    }
                }
                initMenus()

            }
            .addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
    }


    private fun initMenus() {
        QueryRead.menu
            .get()
            .addOnCompleteListener {
                if (it.result != null) {
                    val data = it.result?.toObjects<MenuModel>() as ArrayList

                    menuDashboardRV.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    menuDashboardRV.adapter = MenuAdapter(data)

                    initInformation()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
    }

    private fun initInformation(){
        QueryRead.information
            .get()
            .addOnCompleteListener {
                if (it.result != null){
                    val data = it.result?.toObjects<InformationModel>() as ArrayList
                    informasiDashboardRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    informasiDashboardRV.adapter = InformationAdapter(data)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

            }
    }



}