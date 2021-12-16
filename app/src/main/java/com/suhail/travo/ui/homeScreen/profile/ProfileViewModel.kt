package com.suhail.travo.ui.homeScreen.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhail.travo.data.UserDetails
import com.suhail.travo.roomDB.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
   val database : Database
) : ViewModel() {
    private var userDetails = MutableLiveData<UserDetails>()
    val userDetails_ : LiveData<UserDetails>
            get() = userDetails
    init {
        dataSetToProfileScreen()
    }
    fun dataSetToProfileScreen(){
        viewModelScope.launch {
             userDetails.value= database.travoDao().getUserInfo()
            Log.i("userDetails87678",userDetails.value!!.user_name!!)
        }

    }
}