package com.cikup.santren.presentation.ui.absent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cikup.santren.R
import com.cikup.santren.adapter.ClassAbsentAdapter
import com.cikup.santren.data.model.ClassAbsentModel
import com.cikup.santren.helper.Collection
import com.cikup.santren.helper.Extras
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.presentation.navigation.backToAbsent
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.android.synthetic.main.activity_class.*

class ClassActivity : AppCompatActivity() {

    private var idMajor = ""
    private var absentClass = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)

        idMajor = intent.getStringExtra(Extras.ID_MAJOR).toString()
        absentClass = intent.getStringExtra(Extras.ABSENT_CLASS).toString()
        textView9.text = absentClass

        initAbsent()
    }


    private fun initAbsent(){
        QueryRead.absent
            .document(idMajor)
            .collection(Collection.class_absent)
            .get()
            .addOnCompleteListener {
                if (it.result != null){
                    val data = it.result?.toObjects<ClassAbsentModel>() as ArrayList

                    classRV.apply {
                        layoutManager = LinearLayoutManager(this@ClassActivity, LinearLayoutManager.VERTICAL, false)
                        adapter = ClassAbsentAdapter(this@ClassActivity, idMajor, absentClass, data)
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        backToAbsent(this)
    }
}