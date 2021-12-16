package com.suhail.travo.ui.fragments.registration.launchFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.suhail.travo.databinding.FragmentLaunchBinding
import com.suhail.travo.ui.HomeActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LaunchFragment : Fragment() {
    private val viewModel : LaunchViewModel by viewModels()
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


        viewModel.isValidated.observe(viewLifecycleOwner, Observer {
            if (it)navigateToHomeScreen()
        })


        return launchBinding.root
    }

    private fun navigateToHomeScreen(){
        val intent = Intent(requireContext(),HomeActivity::class.java)
        startActivity(intent)
        activity?.finish()
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