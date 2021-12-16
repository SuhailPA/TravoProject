package com.suhail.travo.data


import com.google.gson.annotations.SerializedName

data class UserRegistrationData(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("user_name")
    val userName: String
)