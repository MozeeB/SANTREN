package com.cikup.santren.data.model

import com.google.firebase.Timestamp
import java.util.*

data class ReportModel(
    val date:Timestamp = Timestamp(Date()),
    val id:String = "",
    val report:String = "",
    val send_by:String = ""
)