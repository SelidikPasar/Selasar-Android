package com.selasarteam.selidikpasar.data.local.datastore

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val name: String? = null,
    val email: String? = null,
    val photo: String
) : Parcelable
