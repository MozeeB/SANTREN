package com.cikup.santren.presentation.ui.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cikup.santren.R
import com.cikup.santren.helper.Field
import com.cikup.santren.helper.QueryAdd
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.presentation.navigation.backToDashboard
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_report.*
import java.util.*

class ReportActivity : AppCompatActivity(), View.OnClickListener {

    private var userName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        kirimBTN.setOnClickListener(this)

        initUserData()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.kirimBTN ->{
                if (kritikEDT.text.toString().isBlank()){
                    Toast.makeText(this, "Mohon isi terlebih dahulu", Toast.LENGTH_LONG).show()
                    return
                }
                val ids = QueryAdd.reports.document().id

                val calender = Calendar.getInstance()

                val data = hashMapOf<String, Any>(
                    Field.id to ids,
                    Field.report to kritikEDT.text.toString(),
                    Field.date to calender.time,
                    Field.send_by to userName
                )

                QueryAdd.reports
                    .document(ids)
                    .set(data)
                    .addOnCompleteListener {
                        backToDashboard(this)
                        Toast.makeText(this, "Berhasil terkirim", Toast.LENGTH_LONG).show()

                    }.addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

                    }

            }
        }
    }

    private fun initUserData(){
        val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
        QueryRead.users
            .document(currentUserId)
            .get()
            .addOnCompleteListener {
                if (it.result != null){
                    val data = it.result!!
                    userName = data[Field.name].toString()
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