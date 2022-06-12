package com.selasarteam.selidikpasar.model.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("articles")
    val articles: List<ArticlesItem>,
)

data class ArticlesItem(

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("source")
    val source: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("summary")
    val summary: String? = null,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("predicted summary")
    val predictedSummary: String? = null
)