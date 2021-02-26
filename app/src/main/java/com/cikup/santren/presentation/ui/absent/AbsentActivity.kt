package com.cikup.santren.presentation.ui.absent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cikup.santren.R
import com.cikup.santren.adapter.MajorAdapter
import com.cikup.santren.data.model.ClassModel
import com.cikup.santren.data.model.MajorModel
import com.cikup.santren.helper.Collection
import com.cikup.santren.helper.QueryRead
import com.cikup.santren.presentation.navigation.backToDashboard
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.android.synthetic.main.activity_absent.*

class AbsentActivity : AppCompatActivity() {

    private var listClass = ArrayList<ClassModel>()
    private var listNameClass = ArrayList<String>()

    private var class_id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_absent)

        initClass()
    }

    private fun initClass(){
        QueryRead.kelas
            .get()
            .addOnCompleteListener {
                if (it.result != null){
                    listClass = it.result!!.toObjects<ClassModel>() as ArrayList
                    listClass.forEach {
                        listNameClass.add(it.class_name)
                    }

                    kelasSPN.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listNameClass)

                    kelasSPN.onItemSelectedListener =   object : AdapterView.OnItemSelectedListener,
                        AdapterView.OnItemClickListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            p2: Int,
                            p3: Long
                        ) {
                            class_id = listClass[p2].class_id
                            initMajor()
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                        }

                        override fun onItemClick(
                            p0: AdapterView<*>?,
                            p1: View?,
                            p2: Int,
                            p3: Long
                        ) {
                        }
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
    }

    private fun initMajor(){
        QueryRead.kelas
            .document(class_id)
            .collection(Collection.major)
            .get()
            .addOnCompleteListener {
                if (it.result != null){
                    val data = it.result?.toObjects<MajorModel>() as ArrayList

                    majorRV.apply {
                        layoutManager = LinearLayoutManager(this@AbsentActivity, LinearLayoutManager.VERTICAL, false)
                        adapter = MajorAdapter(this@AbsentActivity, data)
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
