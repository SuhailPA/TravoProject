package com.suhail.travo.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.suhail.travo.data.UserDetails

@Dao
interface TravoDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user:UserDetails)

    @Query("UPDATE userDetails SET name=:name, user_name=:user_name, email =:email, password =:password")
    suspend fun udatedData(name: String,user_name: String,email: String, password : String)

    @Query("SELECT * FROM userDetails")
    suspend fun getUserInfo():UserDetails
}