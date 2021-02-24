package com.cikup.santren.presentation.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cikup.santren.R
import com.cikup.santren.helper.Field
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.helper.Role
import com.cikup.santren.presentation.navigation.backToLogin
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBTN.setOnClickListener(this)
        toLoginTV.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.registerBTN ->{
                initRegisterUser()
            }
            R.id.toLoginTV ->{
                backToLogin(this)
            }
        }
    }

    private fun initRegisterUser(){
        val nama = namaRegisterEDT.text.toString()
        val email = emailRegisterEDT.text.toString()
        val password = passwordRegisterEDT.text.toString()
        val phone = nomorHpEDT.text.toString()

        if (nama.isBlank()){
            Toast.makeText(this, "Mohon isi nama", Toast.LENGTH_LONG).show()
            return
        }
        if (email.isBlank()){
            Toast.makeText(this, "Mohon isi email", Toast.LENGTH_LONG).show()
            return
        }
        if (password.isBlank()){
            Toast.makeText(this, "Mohon isi password", Toast.LENGTH_LONG).show()
            return
        }
        if (phone.isBlank()){
            Toast.makeText(this, "Mohon isi phone", Toast.LENGTH_LONG).show()
            return
        }

        progressBarHolder.visibility = View.VISIBLE
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid

                    val dataUser = hashMapOf<String, Any>(
                        Field.id_user to currentUserId,
                        Field.name to nama,
                        Field.email to email,
                        Field.phone_number to phone,
                        Field.role to Role.walsan
                    )
                    QueryRead.users
                        .document(currentUserId)
                        .set(dataUser)
                        .addOnCompleteListener {
                            progressBarHolder.visibility = View.GONE

                            firebaseAuth.signOut()
                            Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_LONG).show()
                            backToLogin(this)

                        }
                        .addOnFailureListener {
                            progressBarHolder.visibility = View.GONE
                            firebaseAuth.signOut()
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }

                }else{
                    progressBarHolder.visibility = View.GONE
                    firebaseAuth.signOut()
                    Toast.makeText(this, getString(R.string.register_failed), Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener {
                progressBarHolder.visibility = View.GONE
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

            }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        backToLogin(this)

    }
}