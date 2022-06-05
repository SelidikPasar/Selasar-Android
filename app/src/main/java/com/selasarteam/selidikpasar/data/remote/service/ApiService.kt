package com.selasarteam.selidikpasar.data.remote.service

import com.selasarteam.selidikpasar.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("news")
    suspend fun getSummaryNews(
    ): NewsResponse
}