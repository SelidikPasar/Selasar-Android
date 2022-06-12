package com.selasarteam.selidikpasar.model.remote.service

import com.selasarteam.selidikpasar.model.remote.response.NewsResponse
import com.selasarteam.selidikpasar.model.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("articles")
    suspend fun getSummaryNews(
    ): NewsResponse

    @GET("price")
    suspend fun getPriceList(
        @Header("Authorization") token: String
    ): NewsResponse

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