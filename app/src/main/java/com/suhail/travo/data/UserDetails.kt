package com.suhail.travo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "userDetails")
data class UserDetails(
    var user_name : String?= null,
    var name : String? = null,
    var email : String? = null,
    var password : String? = null,
    var mobile : String? = null,
    var token : String? = null,
    var followingNo : Int? = null,
    var followersNo : Int? = null,
    @PrimaryKey
    var userId : String

)
