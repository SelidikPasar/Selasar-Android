package com.selasarteam.selidikpasar.model.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles")
class NewsEntity(
    @field:ColumnInfo(name = "title")
    @field:PrimaryKey
    val title: String,

    @field:ColumnInfo(name = "summary")
    val summary: String? = null,

    @field:ColumnInfo(name = "author")
    val author: String? = null,

    @field:ColumnInfo(name = "date")
    val date: String,

    @field:ColumnInfo(name = "predictedSummary")
    val predictedSummary: String? = null,

    @field:ColumnInfo(name = "image")
    val image: String? = null,

    @field:ColumnInfo(name = "url")
    val url: String,
) : Parcelable