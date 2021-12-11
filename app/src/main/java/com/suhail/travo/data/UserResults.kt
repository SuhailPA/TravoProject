package com.suhail.travo.data


import com.google.gson.annotations.SerializedName

data class UserResults(
    @SerializedName("result")
    val result: Result
)