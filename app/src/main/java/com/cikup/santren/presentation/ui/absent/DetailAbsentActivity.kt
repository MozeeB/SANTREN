package com.cikup.santren.presentation.ui.absent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import com.cikup.santren.R
import com.cikup.santren.helper.Collection
import com.cikup.santren.helper.Extras
import com.cikup.santren.helper.Field
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.presentation.navigation.backToClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_absent.*

class DetailAbsentActivity : AppCompatActivity() {

    private var idClassAbsent = ""
    private var idMajor = ""
    private var absentClass = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_absent)

        idMajor = intent.getStringExtra(Extras.ID_MAJOR).toString()
        idClassAbsent = intent.getStringExtra(Extras.ID_CLASS_ABSENT).toString()
        absentClass = intent.getStringExtra(Extras.ABSENT_CLASS).toString()

        initDetailAbsent()
    }

    private fun initDetailAbsent(){
        QueryRead.absent
            .document(idMajor)
            .collection(Collection.class_absent)
            .document(idClassAbsent)
            .get()
            .addOnCompleteListener {
                if (it.result != null){
                    val data = it.result!!

                    if (data[Field.image].toString().isNotBlank()){
                        Picasso.get().load(data[Field.image].toString()).into(imageInfoIV)
                    }
                    titleInfoTV.text = data[Field.title].toString()
                    dateInfoTV.text = data[Field.date].toString()
                    contentInfoTV.text = data[Field.description].toString()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val bundle = bundleOf(
            Extras.ID_MAJOR to idMajor,
            Extras.ABSENT_CLASS to absentClass
        )
        backToClass(this, bundle)
    }
}