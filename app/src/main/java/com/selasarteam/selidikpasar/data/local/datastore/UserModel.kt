package com.selasarteam.selidikpasar.data.local.datastore

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val name: String,
    val email: String,
    val photo: String
) : Parcelable
