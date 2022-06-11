package com.selasarteam.selidikpasar.model.remote.service

import com.selasarteam.selidikpasar.model.remote.response.NewsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("news")
    suspend fun getSummaryNews(
    ): NewsResponse
}