package com.suhail.travo.ui.fragments.userRegistration

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhail.travo.data.UserDetails
import com.suhail.travo.data.UserResults
import com.suhail.travo.repositories.RepositoryClass
import com.suhail.travo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserRegistrationViewModel @Inject constructor(
    private val repositoryClass: RepositoryClass
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

    fun registerAsUser(user: UserDetails){
        viewModelScope.launch {
            try {
                var results = repositoryClass.getUserRegisterDetails(user)

               profileDetails.postValue(handleResponceCheck(results))
                updateUserInfo(
                    UserDetails(user.user_name,user.name,user.email,user.password,user.mobile,
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

    fun updateUserInfo(user: UserDetails){
        if(isAllValid.value == true){
            Log.i("checkUser","${user.userId} ||${user.user_name}")
            viewModelScope.launch {
                repositoryClass.userDataUpdate(user)
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
                    return Resource.Error(resultResponce.message!!)
                }
            }
        }else{
            Log.i("Check1","failure")
        }
        return Resource.Error(responce.message())

    }
}