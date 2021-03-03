package com.cikup.santren.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cikup.santren.R
import com.cikup.santren.data.model.MenuModel
import com.cikup.santren.helper.MenuID
import com.cikup.santren.helper.Role
import com.cikup.santren.presentation.navigation.navigateToAbsent
import com.cikup.santren.presentation.navigation.navigateToReport
import com.cikup.santren.presentation.navigation.navigateToReportTeacher
import com.cikup.santren.presentation.navigation.navigationToEvent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuAdapter(
    private val roles: String,
    private val menuModel: ArrayList<MenuModel>
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = menuModel[position]
        val view = holder.itemView

        Picasso.get().load(data.image).into(view.logoMenuIV)
        view.titleMenuTV.text = data.name

        view.setOnClickListener {
            when (data.id) {
                MenuID.jadwal_acara_santri -> {
                    navigationToEvent(it.context)
                }
                MenuID.kritik_dan_saran -> {
                    when(roles){
                        Role.walsan ->{
                            navigateToReport(it.context)
                        }
                        Role.guru ->{
                            navigateToReportTeacher(it.context)
                        }
                    }
                }
                MenuID.laporan_absensi_santri -> {
                    navigateToAbsent(it.context)
                }
            }
        }
    }

    override fun getItemCount(): Int = menuModel.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}