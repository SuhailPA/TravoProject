package com.suhail.travo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.suhail.travo.R
import com.suhail.travo.databinding.FragmentLaunchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LaunchFragment : Fragment() {
    lateinit var launchBinding : FragmentLaunchBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        launchBinding = FragmentLaunchBinding.inflate(layoutInflater,container,false)
        navController = findNavController()
        launchBinding.apply {
            signUpButton.setOnClickListener {
                navigateToRegisterScreen()
            }
            signInButton.setOnClickListener {
                navigateToSignInScreen()
            }

        }




        return launchBinding.root
    }

    private fun navigateToSignInScreen() {
        var action : NavDirections = LaunchFragmentDirections.actionLoginFragmentToSignInFragment()
        navController.navigate(action)

    }


    private fun navigateToRegisterScreen(){
        var action : NavDirections = LaunchFragmentDirections.actionLoginFragmentToPhoneSignUpScreen()
        navController.navigate(action)
    }

}