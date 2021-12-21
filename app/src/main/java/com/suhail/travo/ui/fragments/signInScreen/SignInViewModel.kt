package com.suhail.travo.ui.fragments.signInScreen

import android.content.SharedPreferences
import android.content.pm.SigningInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.suhail.travo.api.BackendAPI
import com.suhail.travo.data.UserDetails
import com.suhail.travo.data.requestData.ForgotPassword
import com.suhail.travo.data.requestData.SignInEmail
import com.suhail.travo.data.responceData.ErrorResponce
import com.suhail.travo.data.responceData.ForgotResponce
import com.suhail.travo.data.responceData.UserResults
import com.suhail.travo.repositories.RepositoryClass
import com.suhail.travo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    sharedPreferences: SharedPreferences,
    private val repositoryClass: RepositoryClass
): ViewModel() {

    val userEmail = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    var isEmailValid : Boolean =false
    var isPasswordValid :Boolean = false
    private val userResults = MutableLiveData<Resource<UserResults>>()
    val userResultLive : LiveData<Resource<UserResults>>
    get() = userResults
    private val forResponce = MutableLiveData<Resource<ForgotResponce>>()
    val for_Responce : LiveData<Resource<ForgotResponce>>
    get() = forResponce


    fun forgotpassWord(forgotPassword: ForgotPassword){
        viewModelScope.launch {
            val result = repositoryClass.forgotPassword(forgotPassword)
            forResponce.postValue(responceHandleForForgotPassword(result))

        }
    }

    fun userEmailSignIn(signIn : SignInEmail){
        viewModelScope.launch {
            userResults.value = responceSignInEmail(repositoryClass.signInEmail(signIn))
        }
    }

    private fun responceSignInEmail(responce: Response<UserResults>): Resource<UserResults>? {
        if (responce.isSuccessful){

            if (responce.code() == 200){
                responce.body()?.let { resultResponce ->
                    val result = responce.body()?.user
                    if (result?.userId!=null)Log.i("userId",result.userId.toString())
                    val userDetails = UserDetails(user_name = result?.userName, name = result?.name, email = result?.email,
                    password = password.value, mobile = result?.mobileNumber, token = responce.body()?.token,
                    followersNo = result?.followers?.size, followingNo = result?.following?.size,
                        userId = result?.userId!!
                    )
                    viewModelScope.launch {
                        repositoryClass.userDataStore(userDetails)
                    }
                    return Resource.Success(resultResponce)
                }
            }
        }else{
            if(responce.errorBody()!=null) Log.i("Check","setttttt ${responce.code()}")
            val gson = Gson()
            val type = object : TypeToken<ErrorResponce>() {}.type
            //Please call errorBody().string() once only as the second try will return an empty string see: https://github.com/square/retrofit/issues/1321#issuecomment-251160231
            val errorResponse: ErrorResponce? = gson.fromJson(responce.errorBody()?.string(), type)
            return Resource.Error(error = errorResponse?.error.toString())
        }
        return Resource.Error(error = responce.errorBody()?.string().toString())
    }

    private fun responceHandleForForgotPassword(responce: Response<ForgotResponce>): Resource<ForgotResponce>? {
        if (responce.isSuccessful){

            if (responce.code() == 200){
                responce.body()?.let { resultResponce ->
                    Log.i("Check","SSsetttttt $resultResponce")
                    return Resource.Success(resultResponce)
                }
            }
        }else{
            if(responce.errorBody()!=null) Log.i("Check","setttttt ${responce.code()}")
            val gson = Gson()
            val type = object : TypeToken<ErrorResponce>() {}.type
            //Please call errorBody().string() once only as the second try will return an empty string see: https://github.com/square/retrofit/issues/1321#issuecomment-251160231
            val errorResponse: ErrorResponce? = gson.fromJson(responce.errorBody()?.string(), type)
            Log.i("Errorrrrr","checking ${errorResponse?.error.toString()}")
            return Resource.Error(error = errorResponse?.error.toString())
        }
        return Resource.Error(error = responce.errorBody()?.string().toString())
    }


}