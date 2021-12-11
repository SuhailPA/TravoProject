package com.suhail.travo.ui.fragments.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.suhail.travo.databinding.FragmentOtpScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpScreenFragment : Fragment() {

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
                navigateToRegistrationScreen()
            }
            sentAgain.setOnClickListener {
                Toast.makeText(requireContext(),"Sending again...",Toast.LENGTH_SHORT).show()
            }
            otpBackButton.setOnClickListener {
                navController.navigateUp()
            }
        }

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