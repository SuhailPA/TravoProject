package com.suhail.travo.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suhail.travo.data.UserDetails

@Database(entities = [UserDetails::class], version = 1)
abstract class Database : RoomDatabase () {

    abstract fun travoDao() : TravoDao
}