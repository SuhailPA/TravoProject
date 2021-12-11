package com.suhail.travo.data


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("followers")
    val followers: List<Any>,
    @SerializedName("following")
    val following: List<Any>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("mobileNumber")
    val mobileNumber: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("__v")
    val v: Int
)