package com.cikup.santren.presentation.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cikup.santren.R
import com.cikup.santren.helper.Field
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.helper.Role
import com.cikup.santren.presentation.navigation.navigateToDashboard
import com.cikup.santren.presentation.navigation.navigateToRegister
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        toRegisterTV.setOnClickListener(this)
        loginBTN.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.toRegisterTV ->{
                navigateToRegister(this)
            }
            R.id.loginBTN ->{
                initLogin()
            }
        }
    }

    private fun initLogin(){
        val email = emailLoginEDT.text.toString()
        val password = passwordLoginEDT.text.toString()

        if (email.isBlank()){
            Toast.makeText(this, "Mohon isi email", Toast.LENGTH_LONG).show()
            return
        }
        if (password.isBlank()){
            Toast.makeText(this, "Mohon isi password", Toast.LENGTH_LONG).show()
            return
        }
        progressBarHolder.visibility = View.VISIBLE
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
                QueryRead.users
                    .document(currentUserId)
                    .get()
                    .addOnCompleteListener {
                        if (it.result != null){
                            val data = it.result!!
                            when(data[Field.role]){
                                Role.walsan ->{
                                    progressBarHolder.visibility = View.GONE
                                    navigateToDashboard(this)
                                    Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_LONG).show()
                                }
                                Role.guru ->{
                                    progressBarHolder.visibility = View.GONE
                                }
                            }
                        }
                    }
                    .addOnFailureListener {
                        progressBarHolder.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

                    }
            }
            .addOnFailureListener {
                progressBarHolder.visibility = View.GONE
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

            }
    }

}