package com.selasarteam.selidikpasar.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "news")
class NewsEntity(
    @field:ColumnInfo(name = "title")
    @field:PrimaryKey
    val title: String,

    @field:ColumnInfo(name = "author")
    val author: String? = null,

    @field:ColumnInfo(name = "publishedAt")
    val publishedAt: String,

    @field:ColumnInfo(name = "content")
    val content: String? = null,

    @field:ColumnInfo(name = "urlToImage")
    val urlToImage: String? = null,

    @field:ColumnInfo(name = "url")
    val url: String,
) : Parcelable