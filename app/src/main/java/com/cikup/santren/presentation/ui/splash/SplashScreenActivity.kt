package com.cikup.santren.presentation.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cikup.santren.R
import com.cikup.santren.helper.Field
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.helper.Role
import com.cikup.santren.presentation.navigation.navigateToDashboard
import com.cikup.santren.presentation.navigation.navigateToLogin
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        CoroutineScope(Dispatchers.IO).launch {
            delay(800)

            if (firebaseAuth.currentUser != null) {
                val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
                QueryRead.users
                    .document(currentUserId)
                    .get()
                    .addOnCompleteListener {
                        if (it.result != null) {
                            val data = it.result!!
                            when (data[Field.role]) {
                                Role.walsan -> {
                                    navigateToDashboard(this@SplashScreenActivity)
                                }
                                Role.guru -> {
                                    navigateToDashboard(this@SplashScreenActivity)
                                }
                            }
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@SplashScreenActivity, it.message, Toast.LENGTH_LONG)
                            .show()
                    }
            } else {
                navigateToLogin(this@SplashScreenActivity)
            }
        }

    }
}