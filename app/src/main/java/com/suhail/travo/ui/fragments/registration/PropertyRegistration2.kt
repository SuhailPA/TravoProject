package com.suhail.travo.ui.fragments.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.suhail.travo.databinding.FragmentPropertyRegistration2Binding


class PropertyRegistration2 : Fragment() {

    lateinit var binding : FragmentPropertyRegistration2Binding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPropertyRegistration2Binding.inflate(layoutInflater,container,false)
        navController = findNavController()
        binding.apply {
            propertyNext2.setOnClickListener {
                val action = PropertyRegistration2Directions.actionPropertyRegistration2ToPropertyRegistration3()
                navController.navigate(action)
            }
        }
        return binding.root
    }

}