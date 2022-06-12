package com.selasarteam.selidikpasar.model.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("loginResult")
    val loginResult: LoginResult? = null
)

data class LoginResult(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("token")
    val token: String
)
