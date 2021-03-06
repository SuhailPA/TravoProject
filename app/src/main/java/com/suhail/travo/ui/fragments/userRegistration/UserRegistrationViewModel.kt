package com.suhail.travo.ui.fragments.userRegistration

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhail.travo.data.UserDetails
import com.suhail.travo.data.requestData.UserRegistrationData
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
class UserRegistrationViewModel @Inject constructor(
    private val repositoryClass: RepositoryClass,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    var userName = MutableLiveData<String>()
    var name_ = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var confirmPassword = MutableLiveData<String>()
    var profileDetails = MutableLiveData<Resource<UserResults>>()
    var isAllValid = MutableLiveData<Boolean>(false)
    val exception = MutableLiveData<String>()

    var isUserName : Boolean = false
    var isName : Boolean = false
    var isEmail : Boolean = false
    var isPassword : Boolean = false
    var isConPassword : Boolean = false

    val userId = MutableLiveData<String>()
    private val userInfo = MutableLiveData<UserDetails>()

    val mobileNumber = MutableLiveData<String>()

    init {
        readUserID()
    }


    fun saveUserInfo(token:String){
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        sharedPreferences.edit().putString("token",token)
    }
    fun registerAsUser(user: UserRegistrationData){
        viewModelScope.launch {
            try {
                Log.i("userDetails","${user.userName},${user.userId}")
                var results = repositoryClass.getUserRegisterDetails(user)

               profileDetails.postValue(handleResponceCheck(results))
                updateUserInfo(
                    UserDetails(user.userName,user.name,user.email,user.password,results.body()?.user?.mobileNumber,
                results.body()?.token,results.body()?.user?.following?.size,results.body()?.user?.followers?.size,
                    user.userId)
                )
            }catch (e:Exception){
                exception.value = e.toString()
            }

        }
    }

    fun isAllValid(){
        isAllValid.value = (isUserName && isName
                && isEmail && isPassword &&
                isConPassword)
    }

    private fun readUserID(){
        viewModelScope.launch {
           userInfo.value = repositoryClass.getUserId()
            userId.value = userInfo.value!!.userId
            mobileNumber.value = userInfo.value?.mobile!!
        }
    }

    private fun updateUserInfo(user: UserDetails){
        if(isAllValid.value == true){
            Log.i("checkUser","${user.userId} ||${user.user_name}")
            viewModelScope.launch {
                repositoryClass.userDataUpdate(user)
                withContext(Dispatchers.Main){

                }
            }

        }
    }

    private fun handleResponceCheck(responce: Response<UserResults>): Resource<UserResults> {
        if (responce.isSuccessful){
            Log.i("Check1","success")
            if (responce.code() == 200){
                responce.body()?.let { resultResponce ->
                    return Resource.Success(resultResponce)
                }
            }
            else{
                responce.body()?.let { resultResponce ->
                    Log.i("logMessage",resultResponce.message.toString())
                    return Resource.Error(error = resultResponce.message!!.toString())

                }
            }
        }else{
            Log.i("Check1","failure")
        }
        return Resource.Error(error = responce.message())

    }
}