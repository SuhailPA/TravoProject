package com.suhail.travo.api

import com.suhail.travo.data.requestData.*
import com.suhail.travo.data.responceData.ForgotResponce
import com.suhail.travo.data.responceData.SignUpReturn
import com.suhail.travo.data.responceData.UserResults
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
    suspend fun userRegister(@Body user : UserRegistrationData) : Response<UserResults>

    @POST("api/auth/reset-password")
    suspend fun forgotPassword(@Body forgotPassword: ForgotPassword) : Response<ForgotResponce>

    @POST("api/auth/signin")
    suspend fun signInEmail(@Body signInEmail: SignInEmail) : Response<UserResults>

    @POST("api/auth/otp-verify")
    suspend fun otpVerification(@Body otpVerify: OtpVerify) : Response<ForgotResponce>
}