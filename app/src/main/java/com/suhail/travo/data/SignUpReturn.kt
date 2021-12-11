package com.suhail.travo.data


import com.google.gson.annotations.SerializedName

data class SignUpReturn(
    @SerializedName("message")
    val message: String,
    @SerializedName("user")
    val user: User?
)