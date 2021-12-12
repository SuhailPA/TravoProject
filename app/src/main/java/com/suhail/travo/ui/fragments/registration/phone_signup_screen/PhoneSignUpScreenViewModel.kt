package com.suhail.travo.ui.fragments.registration.phone_signup_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhail.travo.data.SignUpDetails
import com.suhail.travo.data.SignUpReturn
import com.suhail.travo.data.User
import com.suhail.travo.data.UserDetails
import com.suhail.travo.repositories.RepositoryClass
import com.suhail.travo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class PhoneSignUpScreenViewModel @Inject constructor(
    private val repositoryClass: RepositoryClass
) : ViewModel(){

    var phoneNumber:MutableLiveData<String> = MutableLiveData()
    private var signUpResult : MutableLiveData<Resource<SignUpReturn>> = MutableLiveData()
    val _signUpResult : LiveData<Resource<SignUpReturn>>
        get() = signUpResult
    private var isSignUp : MutableLiveData<Boolean> = MutableLiveData()
        val _isSignUp : LiveData<Boolean>
        get() = isSignUp

    var testVariable = MutableLiveData<String>()

    fun signUp(signUpDetails: SignUpDetails){
        viewModelScope.launch {
            val result = repositoryClass.userSignIn(signUpDetails)
            signUpResult.value = handleResponceCheck(result)

        }
    }

    private fun handleResponceCheck(responce: Response<SignUpReturn>): Resource<SignUpReturn> {
        if (responce.isSuccessful){
            Log.i("Check","success")
            if (responce.code() == 200){
                responce.body()?.let { resultResponce ->
                    isSignUp.value = true
                    val user = UserDetails(null,null,null,null,
                        responce.body()!!.user!!.id)
                    viewModelScope.launch {
                        repositoryClass.userDataStore(user)
                        withContext(Dispatchers.Main){
                            Log.i("userIDdatabase","success ${responce.body()!!.user!!.id}")
                        }
                    }
                    return Resource.Success(resultResponce)
                }
            }else{
                    responce.body()?.let { resultResponce ->
                        isSignUp.value = false
                        return Resource.Error(resultResponce.message)
                    }
                }
        }else{
            Log.i("Check","failure")
        }
        return Resource.Error(responce.message())

    }

    fun resetAll(){
        isSignUp.value = false
    }


}