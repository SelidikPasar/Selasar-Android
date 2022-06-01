package com.selasarteam.selidikpasar.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
class UserEntity(
    @field:ColumnInfo(name = "token")
    @field:PrimaryKey
    val token: String,

    @field:ColumnInfo(name = "name")
    val name: String? = null,

    @field:ColumnInfo(name = "email")
    val email: String? = null,

    @field:ColumnInfo(name = "photo")
    val photo: String? = null
) : Parcelable
