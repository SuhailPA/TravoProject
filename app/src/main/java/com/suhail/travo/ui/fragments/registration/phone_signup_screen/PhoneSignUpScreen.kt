package com.suhail.travo.ui.fragments.registration.phone_signup_screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.suhail.travo.R
import com.suhail.travo.data.SignUpDetails
import com.suhail.travo.databinding.FragmentPhoneSignUpScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhoneSignUpScreen : Fragment() {

    private val viewModel : PhoneSignUpScreenViewModel by viewModels()
    var binding : FragmentPhoneSignUpScreenBinding? = null
    lateinit var navController: NavController
    lateinit var currentUser : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        binding =  FragmentPhoneSignUpScreenBinding.inflate(layoutInflater,container,false)
        navController = findNavController()
        var imm:InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding?.phoneSignUpScreen = viewModel
        viewModel.phoneNumber.observe(viewLifecycleOwner, Observer {
            if (it.length<10){
                binding?.mobileNumberField?.error = "Minimum 10 characters are required"
                imm.showSoftInput(binding?.continueToOtpScreen, 0)
                binding?.continueToOtpScreen?.isEnabled = false
                binding?.continueToOtpScreen?.isClickable = false
                binding?.continueToOtpScreen?.setBackgroundColor(ContextCompat.getColor(this.requireContext(), R.color.grey))
            }
            else {
                binding?.continueToOtpScreen?.apply {
                    isEnabled = true
                    isClickable = true
                    setBackgroundColor(ContextCompat.getColor(this.context, R.color.white))
                    imm.hideSoftInputFromWindow(binding?.continueToOtpScreen?.windowToken,0 )
                }
            }
        })


        binding?.apply {
            continueToOtpScreen.setOnClickListener {
                Log.i("Clicked","true")
                val number = mobileNumberField.text.toString().toString()
                currentUser = if (registerAsUser.isChecked)"user"
                else "propertyOwner"
                val signUpDetails = SignUpDetails(currentUser,number)
                viewModel.signUp(signUpDetails)

            }
        }

        viewModel.testVariable.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        })
        viewModel._isSignUp.observe(viewLifecycleOwner, Observer {
            if (it && currentUser == "user"){
                val action = PhoneSignUpScreenDirections.actionPhoneSignUpScreenToOtpScreenFragment("user")
                navController.navigate(action)
                viewModel.resetAll()
            }
        })

        viewModel._signUpResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),it.data?.message.toString(),Toast.LENGTH_SHORT).show()
        })
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}