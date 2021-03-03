package com.cikup.santren.helper

import com.google.firebase.firestore.FirebaseFirestore


object Collection{
    val users = "users"
    val menu = "menu"
    val information = "information"
    val event = "event"
    val reports = "reports"
    val kelas = "class"
    val major = "major"
    val absent = "absent"
    val class_absent = "class_absent"
}

object QueryRead{
    val users = FirebaseFirestore.getInstance().collection(Collection.users)
    val menu = FirebaseFirestore.getInstance().collection(Collection.menu)
    val information = FirebaseFirestore.getInstance().collection(Collection.information)
    val event = FirebaseFirestore.getInstance().collection(Collection.event)
    val kelas = FirebaseFirestore.getInstance().collection(Collection.kelas)
    val absent = FirebaseFirestore.getInstance().collection(Collection.absent)
    val reports = FirebaseFirestore.getInstance().collection(Collection.reports)
}
object QueryAdd{
    val reports = FirebaseFirestore.getInstance().collection(Collection.reports)
}
object Field{

    //Users Collection
    val id_user = "id_user"
    val name = "name"
    val email = "email"
    val phone_number = "phone_number"
    val role = "role"

    //information
    val id = "id"
    val author = "author"
    val content = "content"
    val date = "date"
    val image = "image"
    val title = "title"

    val description = "description"

    //reports

    val report = "report"
    val send_by = "send_by"
}

object Role{
    val walsan = "Walsan"
    val guru = "Guru"
}

object Extras{
    val ID_INFO = "id_info"
    val ID_MAJOR = "id_major"
    val ID_CLASS_ABSENT = "id_class_absent"
    val ABSENT_CLASS = "absent_class"
}
object MenuID{
    val laporan_absensi_santri = "FbE3hzKurZsrUrtCz1vo"
    val kritik_dan_saran = "V1437x9lQqG18DXAQdoy"
    val jadwal_acara_santri = "nNNCsrGQqlWs4NZVUgNO"
}