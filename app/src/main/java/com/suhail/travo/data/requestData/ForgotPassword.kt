package com.suhail.travo.data.requestData


import com.google.gson.annotations.SerializedName

data class ForgotPassword(
    @SerializedName("email")
    val email: String
)