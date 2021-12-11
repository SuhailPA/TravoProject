package com.suhail.travo.ui.fragments.registration.registration_screen_zero

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suhail.travo.repositories.RepositoryClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationZeroScreenViewModel @Inject constructor(
    val repositoryClass: RepositoryClass
) : ViewModel(){
    val ownerName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPass = MutableLiveData<String>()


}