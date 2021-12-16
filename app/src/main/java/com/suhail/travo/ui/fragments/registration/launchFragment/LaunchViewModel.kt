package com.suhail.travo.ui.fragments.registration.launchFragment

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    val isValidated = MutableLiveData<Boolean>()
    init {
        checkUserDetails()
    }
    fun checkUserDetails(){
        isValidated.value = sharedPreferences.contains("isLoggedIn") && (sharedPreferences.getBoolean("isLoggedIn",false))
    }
}