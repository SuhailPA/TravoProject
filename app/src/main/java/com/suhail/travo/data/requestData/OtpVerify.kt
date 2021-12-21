package com.suhail.travo.data.requestData


import com.google.gson.annotations.SerializedName

data class OtpVerify(
    @SerializedName("mobileNo")
    val mobileNo: String,
    @SerializedName("otp")
    val otp: String
)