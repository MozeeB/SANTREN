package com.cikup.santren.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.cikup.santren.R
import com.cikup.santren.data.model.ClassAbsentModel
import com.cikup.santren.helper.Extras
import com.cikup.santren.presentation.navigation.navigateToDetailAbsent
import kotlinx.android.synthetic.main.item_class.view.*

class ClassAbsentAdapter(
    private val context: Context,
    private val idMajor:String,
    private val absentClass:String,
    private val classAbsentModel: ArrayList<ClassAbsentModel>
) : RecyclerView.Adapter<ClassAbsentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_class, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = classAbsentModel[position]
        val view = holder.itemView

        view.titleClassTV.text = data.title
        view.dateAbsentTV.text = data.date

        view.setOnClickListener {
            val bundle = bundleOf(
                Extras.ID_CLASS_ABSENT to data.id,
                Extras.ID_MAJOR to idMajor,
                Extras.ABSENT_CLASS to absentClass
            )
            navigateToDetailAbsent(context, bundle)
        }

    }

    override fun getItemCount(): Int = classAbsentModel.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}