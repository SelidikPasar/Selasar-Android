package com.selasarteam.selidikpasar.model.remote.service

import com.selasarteam.selidikpasar.model.remote.response.NewsResponse
import com.selasarteam.selidikpasar.model.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("articles")
    suspend fun getSummaryNews(
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