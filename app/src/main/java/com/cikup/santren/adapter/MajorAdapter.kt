package com.cikup.santren.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.cikup.santren.R
import com.cikup.santren.data.model.MajorModel
import com.cikup.santren.helper.Extras
import com.cikup.santren.presentation.navigation.navigateToClass
import kotlinx.android.synthetic.main.item_major.view.*

class MajorAdapter(
    private val context: Context,
    private val listMajor:ArrayList<MajorModel>
) : RecyclerView.Adapter<MajorAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_major, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listMajor[position]
        val view = holder.itemView

        view.majorNameTV.text = data.absent_class


        view.setOnClickListener{
            val bundle = bundleOf(
                Extras.ID_MAJOR to data.id,
                Extras.ABSENT_CLASS to data.absent_class
            )
            navigateToClass(context, bundle)
        }

    }

    override fun getItemCount(): Int = listMajor.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}