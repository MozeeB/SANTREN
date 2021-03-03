package com.cikup.santren.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cikup.santren.R
import com.cikup.santren.data.model.ReportModel
import kotlinx.android.synthetic.main.item_report.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ReportAdapter(
    private val listReports: ArrayList<ReportModel>
) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_report, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listReports[position]
        val view = holder.itemView

        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        view.reportContentTV.text = data.report
        view.sendByNameTV.text = "Oleh : ${data.send_by}"

        view.dateTimeTV.text = dateFormat.format(data.date.toDate().time)
    }

    override fun getItemCount(): Int = listReports.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}