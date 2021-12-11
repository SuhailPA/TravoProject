package com.suhail.travo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "userDetails")
data class UserDetails(
    var user_name : String?,
    var name : String?,
    var email : String?,
    var password : String?,
    @PrimaryKey
    var userId : String

)
