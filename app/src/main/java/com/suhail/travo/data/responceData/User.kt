package com.suhail.travo.data.responceData


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("followers")
    val followers: List<Any>,
    @SerializedName("following")
    val following: List<Any>,
    @SerializedName("mobileNumber")
    val mobileNumber: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("_id")
    val userId : String ?= null
)