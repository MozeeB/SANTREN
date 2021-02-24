package com.cikup.santren.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cikup.santren.R
import com.cikup.santren.data.model.EventModel
import kotlinx.android.synthetic.main.item_event.view.*

class EventAdapter(
    private val eventModel: ArrayList<EventModel>
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = eventModel[position]
        val view = holder.itemView

        view.titleEvent.text = data.title
        view.dateEventTV.text = data.date
        view.descriptionEventTV.text = data.description
    }

    override fun getItemCount(): Int  = eventModel.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}