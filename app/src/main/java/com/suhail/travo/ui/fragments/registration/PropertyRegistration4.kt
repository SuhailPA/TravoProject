package com.suhail.travo.ui.fragments.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.suhail.travo.databinding.FragmentPropertyRegistration4Binding


class PropertyRegistration4 : Fragment() {

    lateinit var binding : FragmentPropertyRegistration4Binding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPropertyRegistration4Binding.inflate(layoutInflater,container,false)
        navController = findNavController()
        binding.apply {
            propertyNext4.setOnClickListener {
                val action = PropertyRegistration4Directions.actionPropertyRegistration4ToPropertyRegistration5()
                navController.navigate(action)
            }
        }
        return binding.root
    }

}