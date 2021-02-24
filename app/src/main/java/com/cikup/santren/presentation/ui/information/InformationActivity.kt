package com.cikup.santren.presentation.ui.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cikup.santren.R
import com.cikup.santren.adapter.InformationAdapter
import com.cikup.santren.data.model.InformationModel
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.presentation.navigation.backToDashboard
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.android.synthetic.main.activity_information.*
class InformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)


        initInformation()
    }

    private fun initInformation(){
        QueryRead.information
                .get()
                .addOnCompleteListener {
                    if (it.result != null){
                        val data = it.result?.toObjects<InformationModel>() as ArrayList
                        informationDetailRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                        informationDetailRV.adapter = InformationAdapter(data)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

                }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        backToDashboard(this)
    }
}