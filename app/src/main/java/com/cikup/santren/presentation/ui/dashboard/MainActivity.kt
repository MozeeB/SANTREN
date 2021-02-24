package com.cikup.santren.presentation.ui.dashboard

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cikup.santren.R
import com.cikup.santren.adapter.InformationAdapter
import com.cikup.santren.adapter.MenuAdapter
import com.cikup.santren.data.model.InformationModel
import com.cikup.santren.data.model.MenuModel
import com.cikup.santren.helper.Field
import com.cikup.santren.helper.QueryRead
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUser()
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