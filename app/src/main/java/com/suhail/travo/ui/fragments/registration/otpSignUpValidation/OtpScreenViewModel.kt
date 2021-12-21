package com.suhail.travo.ui.fragments.registration.otpSignUpValidation

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mukesh.OtpView
import com.suhail.travo.data.requestData.OtpVerify
import com.suhail.travo.data.responceData.ErrorResponce
import com.suhail.travo.data.responceData.ForgotResponce
import com.suhail.travo.data.responceData.UserResults
import com.suhail.travo.repositories.RepositoryClass
import com.suhail.travo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class OtpScreenViewModel @Inject constructor(
    val state : SavedStateHandle,
    val repositoryClass: RepositoryClass

): ViewModel() {
    val mobileNumber = state.get<String>("mobileNumber")
    val otpResponce = MutableLiveData<Resource<ForgotResponce>>()

    fun otpVerification(otp : String){
        val otpVerify = OtpVerify(mobileNumber!!,otp)
        viewModelScope.launch {
            val responce = repositoryClass.otpVerification(otpVerify)
            otpResponce.postValue(handleResponceCheck(responce))
        }
    }

    private fun handleResponceCheck(responce: Response<ForgotResponce>): Resource<ForgotResponce> {
        if (responce.isSuccessful){
            Log.i("Check1","success")
            if (responce.code() == 200){
                responce.body()?.let { resultResponce ->
                    return Resource.Success(resultResponce)
                }
            }
            else{

                    val gson = Gson()
                    val type = object : TypeToken<ErrorResponce>() {}.type
                    //Please call errorBody().string() once only as the second try will return an empty string see: https://github.com/square/retrofit/issues/1321#issuecomment-251160231
                    val errorResponse: ErrorResponce? = gson.fromJson(responce.errorBody()?.string(), type)
                    return Resource.Error(error = errorResponse?.error.toString())


            }
        }else{
            Log.i("Check1","failure")
        }
        return Resource.Error(error = responce.message())

    }
}