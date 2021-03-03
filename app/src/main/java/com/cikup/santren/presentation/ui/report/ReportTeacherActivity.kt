package com.cikup.santren.presentation.ui.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cikup.santren.R
import com.cikup.santren.adapter.ReportAdapter
import com.cikup.santren.data.model.ReportModel
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.presentation.navigation.backToDashboard
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.android.synthetic.main.activity_report_teacher.*

class ReportTeacherActivity : AppCompatActivity() {

    private var listReports:ArrayList<ReportModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_teacher)

        initReports()
    }

    private fun initReports(){
        QueryRead.reports
            .get()
            .addOnCompleteListener {
                if (it.result != null){
                    listReports = it.result?.toObjects<ReportModel>() as ArrayList

                    reportTeacherRV.apply {
                        layoutManager = LinearLayoutManager(this@ReportTeacherActivity, LinearLayoutManager.VERTICAL, false)
                        adapter = ReportAdapter(listReports)
                    }
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