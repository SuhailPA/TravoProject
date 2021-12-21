package com.suhail.travo.data.responceData

import com.google.gson.annotations.SerializedName


data class ErrorResponce(
    @SerializedName("error")
    val error : String?
)
