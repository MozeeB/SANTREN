package com.cikup.santren.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cikup.santren.R
import com.cikup.santren.data.model.InformationModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_information.view.*

class InformationAdapter(
    private val informationModel: ArrayList<InformationModel>
) : RecyclerView.Adapter<InformationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_information, parent, false)
        )

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = informationModel[position]
        val view = holder.itemView

        Picasso.get().load(data.image).into(view.informationIV)
        view.titleInfoTV.text = data.title
    }

    override fun getItemCount(): Int = informationModel.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}