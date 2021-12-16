package com.suhail.travo.repositories

import com.suhail.travo.api.BackendAPI
import com.suhail.travo.data.*
import com.suhail.travo.data.requestData.SignUpDetails
import com.suhail.travo.roomDB.Database
import retrofit2.Response
import javax.inject.Inject

class RepositoryClass @Inject constructor(
    private val api : BackendAPI,
    private val database: Database
) {
    suspend fun userSignIn(user: SignUpDetails):Response<SignUpReturn>{
       return api.userSignUpOtp(user)
    }

    suspend fun userDataStore(user: UserDetails) {
        database.travoDao().insertUser(user)
    }

    suspend fun userDataUpdate(user: UserDetails){
        database.travoDao().udatedData(user.name!!,user.user_name!!,user.email!!,user.password!!)
    }

    suspend fun getUserId() : UserDetails{
        return database.travoDao().getUserInfo()
    }

    suspend fun getUserRegisterDetails(user:UserRegistrationData):Response<UserResults>{
       return api.userRegister(user)
    }

}