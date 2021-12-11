package com.suhail.travo.ui.fragments.registration.registration_screen_zero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.suhail.travo.databinding.FragmentRegistrationScreenZeroBinding
import com.suhail.travo.ui.fragments.registration.phone_signup_screen.PhoneSignUpScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationScreenZero : Fragment() {


    private val viewModel : RegistrationZeroScreenViewModel by viewModels()
    lateinit var binding : FragmentRegistrationScreenZeroBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationScreenZeroBinding.inflate(layoutInflater,container,false)
        navController = findNavController()
        binding.propertyNext0.setOnClickListener {
            val action = RegistrationScreenZeroDirections.actionRegistrationScreenZeroToPropertyRegistrationFirst()
            navController.navigate(action)
        }

        binding.registrationZero = viewModel

        viewModel.ownerName.observe(viewLifecycleOwner, Observer {
            if (it.length<2)binding.propertyOwnerName.error = "Owner name should be at least 3 letters"
        })
        return binding.root
    }


}