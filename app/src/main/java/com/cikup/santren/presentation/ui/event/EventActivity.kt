package com.cikup.santren.presentation.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cikup.santren.R
import com.cikup.santren.adapter.EventAdapter
import com.cikup.santren.data.model.EventModel
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.presentation.navigation.backToDashboard
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        initEvent()
    }

    private fun initEvent(){
        QueryRead.event
            .get()
            .addOnCompleteListener {
                if (it.result != null){
                    val data = it.result!!.toObjects<EventModel>() as ArrayList

                    eventRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    eventRV.adapter = EventAdapter(data)
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