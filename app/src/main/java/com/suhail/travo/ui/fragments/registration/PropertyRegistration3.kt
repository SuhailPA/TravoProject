package com.suhail.travo.ui.fragments.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.suhail.travo.databinding.FragmentPropertyRegistration3Binding


class PropertyRegistration3 : Fragment() {
    lateinit var binding: FragmentPropertyRegistration3Binding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPropertyRegistration3Binding.inflate(layoutInflater,container,false)
        navController = findNavController()
        binding.apply {
            propertyNext3.setOnClickListener {
                val action = PropertyRegistration3Directions.actionPropertyRegistration3ToPropertyRegistration4()
                navController.navigate(action)
            }
        }
        return binding.root

    }

}