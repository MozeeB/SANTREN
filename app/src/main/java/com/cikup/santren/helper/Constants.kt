package com.cikup.santren.helper

import com.google.firebase.firestore.FirebaseFirestore


object Collection{
    val users = "users"
    val menu = "menu"
    val information = "information"
}

object QueryRead{
    val users = FirebaseFirestore.getInstance().collection(Collection.users)
    val menu = FirebaseFirestore.getInstance().collection(Collection.menu)
    val information = FirebaseFirestore.getInstance().collection(Collection.information)
}
object Field{

    //Users Collection
    val id_user = "id_user"
    val name = "name"
    val email = "email"
    val phone_number = "phone_number"
    val role = "role"
}

object Role{
    val walsan = "Walsan"
    val guru = "Guru"
}