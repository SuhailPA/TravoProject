package com.suhail.travo.ui.fragments.userRegistration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.suhail.travo.R
import com.suhail.travo.data.requestData.UserRegistrationData
import com.suhail.travo.databinding.FragmentUserRegistrationBinding
import com.suhail.travo.ui.HomeActivity
import com.suhail.travo.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRegistration : Fragment() {

    lateinit var navController: NavController
    private val viewModel : UserRegistrationViewModel by viewModels()
   lateinit var binding : FragmentUserRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRegistrationBinding.inflate(layoutInflater,container,false)
        var conPass:String ?= null

        binding.userRegistrationScreen = viewModel
        // Inflate the layout for this fragment

        viewModel.userName.observe(viewLifecycleOwner, Observer {
            if (it.length<3){
                binding.userName.error = "UserName should be atleast 4 characters"
                viewModel.isUserName = false
            }
            else {
                viewModel.isUserName = true

            }
            viewModel.isAllValid()
        })

        viewModel.name_.observe(viewLifecycleOwner, Observer {
            if (it.length<2){
                binding.nameRegistration.error = "Name should be atleast 3 characters"
                viewModel.isName = false
            }
            else {
                viewModel.isName = true
            }
            viewModel.isAllValid()
        })

        viewModel.email.observe(viewLifecycleOwner, Observer {
            if (it.length<0){
                binding.userEmail.error = "Email can't be empty"
                viewModel.isEmail = false
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                binding.userEmail.error = "Input does'nt matches the email format"
                viewModel.isEmail = false
            }
            else{
                viewModel.isEmail = true
            }
            viewModel.isAllValid()
        })

        viewModel.password.observe(viewLifecycleOwner, Observer {
            conPass = it

            viewModel.confirmPassword.observe(viewLifecycleOwner, Observer {
                if (!conPass?.equals(it)!!){
                    binding.userConfirmPassword.error = "Both passwords should be equal"
                    viewModel.isConPassword = false
                }
                else{
                    viewModel.isConPassword = true
                }
                viewModel.isAllValid()
            })

            when {
                it.length<7 -> {
                    binding.userPassword.error = "Password should be atleast 8 characters"
                    viewModel.isPassword = false
                }
                it.length>16 -> {
                    binding.userPassword.error = "Password length should be under 16 characters"
                    viewModel.isPassword = false
                }
                else -> {
                    viewModel.isPassword = true
                }
            }
            viewModel.isAllValid()
        })

        binding.userSubmit.setOnClickListener {
            val userDet = viewModel.userName.value?.let { it1 ->
                viewModel.name_.value?.let { it2 ->
                    viewModel.password.value?.let { it3 ->
                        viewModel.email.value?.let { it4 ->
                            UserRegistrationData(userName = it1, name = it2, email = it4,
                                password = it3, userId = viewModel.userId.value!!)
                        }
                    }
                }
            }
            Log.i("userIdvalue",viewModel.userId.value!!)
            if (userDet != null) {
                viewModel.registerAsUser(userDet)
            }
            viewModel.profileDetails.observe(viewLifecycleOwner, Observer { it ->
                when(it){
                    is Resource.Success ->{
                        it.data.let {
                            val intent = Intent(requireContext(), HomeActivity::class.java)
                            startActivity(intent)
                            viewModel.saveUserInfo(it?.token!!)
                        }
                    }
                    is Resource.Error ->{
                        it.error.let { msg->
                            Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }



        viewModel.isAllValid.observe(viewLifecycleOwner, Observer {
            binding.userSubmit.isEnabled = it
            if (it) binding.userSubmit.setBackgroundColor(ContextCompat.getColor(this.requireContext(), R.color.white))
            else  binding.userSubmit.setBackgroundColor(ContextCompat.getColor(this.requireContext(), R.color.grey))
        })
        return binding.root
    }

}