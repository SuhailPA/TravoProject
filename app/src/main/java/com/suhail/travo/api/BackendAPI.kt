package com.suhail.travo.api

import com.suhail.travo.data.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BackendAPI {
    companion object{
        const val BASE_URL = "https://travosocialmedia.herokuapp.com/"
    }
    @POST("api/auth/signup")
    suspend fun userSignUpOtp(@Body signUpDetails : SignUpDetails):Response<SignUpReturn>

    @POST("api/auth/mobile_signup")
    suspend fun userRegister(@Body user : UserDetails) : Response<UserResults>

}