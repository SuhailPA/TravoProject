package com.suhail.travo.data.responceData


import com.google.gson.annotations.SerializedName

data class SignUpReturn(
    @SerializedName("message")
    val message: String,
    @SerializedName("_id")
    val id : String?
)