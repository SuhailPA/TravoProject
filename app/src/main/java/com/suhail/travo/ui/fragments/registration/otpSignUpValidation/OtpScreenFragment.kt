package com.suhail.travo.ui.fragments.registration.otpSignUpValidation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mukesh.OnOtpCompletionListener
import com.mukesh.OtpView
import com.suhail.travo.data.requestData.OtpVerify
import com.suhail.travo.databinding.FragmentOtpScreenBinding
import com.suhail.travo.util.Resource
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class OtpScreenFragment : Fragment() {

    private val viewModel : OtpScreenViewModel by viewModels()
    private val args: OtpScreenFragmentArgs by navArgs<OtpScreenFragmentArgs>()


    lateinit var binding: FragmentOtpScreenBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpScreenBinding.inflate(inflater,container,false)
        navController = findNavController()
        binding.apply {
            submitOtp.setOnClickListener {

            }
            sentAgain.setOnClickListener {
                Toast.makeText(requireContext(),"Sending again...",Toast.LENGTH_SHORT).show()
            }
            otpBackButton.setOnClickListener {
                navController.navigateUp()
            }
        }



        binding.otpView.setOtpCompletionListener {
            viewModel.otpVerification(it)
        }

        viewModel.otpResponce.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    Toast.makeText(requireContext(),it.data?.message,Toast.LENGTH_SHORT).show()
                    navigateToRegistrationScreen()
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(),it.error.toString(),Toast.LENGTH_SHORT).show()
                }
                else-> {
                    Toast.makeText(requireContext(),"something went wrong",Toast.LENGTH_SHORT).show()
                }
            }
        })

        return binding.root
    }

    private fun navigateToRegistrationScreen() {

        if (args.user == "user"){
            var action = OtpScreenFragmentDirections.actionOtpScreenFragmentToUserRegistration()
            navController.navigate(action)
        }else{
            var action = OtpScreenFragmentDirections.actionOtpScreenFragmentToRegistrationScreenZero()
            navController.navigate(action)
        }

    }


}