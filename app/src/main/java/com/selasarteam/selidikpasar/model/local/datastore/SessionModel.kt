package com.selasarteam.selidikpasar.model.local.datastore

data class SessionModel(
    val name: String,
    val email: String,
    val photo: String,
    val token: String,
    val isLogin: Boolean
)