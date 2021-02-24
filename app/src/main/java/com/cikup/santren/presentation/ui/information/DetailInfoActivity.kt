package com.cikup.santren.presentation.ui.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cikup.santren.R
import com.cikup.santren.helper.Extras
import com.cikup.santren.helper.Field
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.presentation.navigation.backToInformation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_info.*

class DetailInfoActivity : AppCompatActivity() {

    private var idInfo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_info)

        idInfo = intent.getStringExtra(Extras.ID_INFO).toString()

        initDetailInfo()

    }

    private fun initDetailInfo(){
        QueryRead.information
            .whereEqualTo(Field.id, idInfo)
            .get()
            .addOnCompleteListener {
                if (it.result != null){
                    val data = it.result!!.documents[0]
                    Picasso.get().load(data[Field.image].toString()).into(imageInfoIV)
                    titleInfoTV.text = data[Field.title].toString()
                    dateInfoTV.text = data[Field.date].toString()
                    contentInfoTV.text = data[Field.content].toString()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        backToInformation(this)
    }
}