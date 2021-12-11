package com.suhail.travo.data


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("followers")
    val followers: List<Any>,
    @SerializedName("following")
    val following: List<Any>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("mobileNumber")
    val mobileNumber: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("__v")
    val v: Int
)