package com.suhail.travo.data


import com.google.gson.annotations.SerializedName

data class UserResults(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("message")
    val message : String ?= null
)