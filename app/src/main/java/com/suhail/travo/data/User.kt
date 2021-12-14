package com.suhail.travo.data


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
    val userName: String
)