package com.selasarteam.selidikpasar.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("LoginResponse")
    val loginResponse: List<LoginResponseItem?>? = null
)

data class LoginResponseItem(

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)
