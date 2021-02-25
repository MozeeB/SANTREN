package com.cikup.santren.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cikup.santren.R
import com.cikup.santren.data.model.MajorModel
import kotlinx.android.synthetic.main.item_major.view.*

class MajorAdapter(
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

    }

    override fun getItemCount(): Int = listMajor.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}