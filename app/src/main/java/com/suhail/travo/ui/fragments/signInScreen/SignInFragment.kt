package com.suhail.travo.ui.fragments.signInScreen

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.suhail.travo.databinding.FragmentSignInBinding
import com.suhail.travo.ui.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import android.widget.EditText
import android.widget.Toast
import com.suhail.travo.data.requestData.ForgotPassword
import com.suhail.travo.data.requestData.SignInEmail
import com.suhail.travo.data.responceData.ForgotResponce
import com.suhail.travo.util.Resource


@AndroidEntryPoint
class SignInFragment : Fragment() {
    lateinit var binding : FragmentSignInBinding
    lateinit var email : String
    lateinit var password: String
    val viewModel : SignInViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater,container,false)


        binding.signInViewModel = viewModel

        viewModel.userEmail.observe(viewLifecycleOwner, Observer {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches())
            {
                binding.emailSignIn.error = "Email should be in correct pattern"
            }
            else{
                email = it
                viewModel.isEmailValid = true
            }
        })
        viewModel.password.observe(viewLifecycleOwner, Observer {
            if (it.length<8)binding.passwordSignIn.error = "password length should be 8 characters"
            else {
                password = it
                viewModel.isPasswordValid = true
            }
        })
        binding.signInButton.setOnClickListener {
            if (viewModel.isEmailValid && viewModel.isPasswordValid){
                val userEmail = SignInEmail(viewModel.userEmail.value!!,viewModel.password.value!!)
                viewModel.userEmailSignIn(userEmail)
            }

        }

        viewModel.userResultLive.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    val intent = Intent(requireContext(),HomeActivity::class.java)
                    startActivity(intent)
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(),it.error.toString(),Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.forgotPassword.setOnClickListener{
            val editText = EditText(requireContext())
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("ForgotPassword")
                .setMessage("We 'll be sending a link to the email id where you can reset your password")
                .setMessage("Enter your registered Email Id")
                .setView(editText)
                .setPositiveButton("Sent",DialogInterface.OnClickListener { dialogInterface, i ->
                    val emailId = editText.text.toString()
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches()){
                        editText.error = "Email Id is in wrong format"
                    }else{
                        val forPass = ForgotPassword(emailId)
                        viewModel.forgotpassWord(forPass)
                        dialogInterface.dismiss()
                    }
                })
                .setNegativeButton("Cancel",DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .setCancelable(false)
                .show()
        }
        viewModel.for_Responce.observe(viewLifecycleOwner, Observer {
            when(it){
              is Resource.Success ->{
                  Log.i("responceCheck","success")
                  Toast.makeText(requireContext(),it.data?.message,Toast.LENGTH_SHORT).show()
              }
                is Resource.Error -> {
                    Log.i("responceCheck","Error")
                    Toast.makeText(requireContext(),it.error.toString(),Toast.LENGTH_SHORT).show()
                }
                else ->{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }
            }
        })
        return binding.root
    }



}