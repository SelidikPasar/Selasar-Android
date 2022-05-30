package com.selasarteam.selidikpasar.data.local.datastore

data class SessionModel(
    val name: String,
    val token: String,
    val isLogin: Boolean
)