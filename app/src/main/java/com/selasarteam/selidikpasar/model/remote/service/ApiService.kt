package com.selasarteam.selidikpasar.model.remote.service

import com.selasarteam.selidikpasar.model.remote.response.MarketResponse
import com.selasarteam.selidikpasar.model.remote.response.NewsResponse
import com.selasarteam.selidikpasar.model.remote.response.PriceResponse
import com.selasarteam.selidikpasar.model.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("articles")
    suspend fun getSummaryNews(
    ): NewsResponse

    @GET("price")
    fun getPriceList(
    ): Call<PriceResponse>

    @GET("market")
    fun getMarketList(
        @Header("Authorization") token: String
    ): Call<MarketResponse>

    @FormUrlEncoded
    @POST("register")
    fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserResponse>

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserResponse>
}