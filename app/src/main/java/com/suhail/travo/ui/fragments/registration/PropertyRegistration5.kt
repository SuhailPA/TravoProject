package com.suhail.travo.ui.fragments.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.suhail.travo.databinding.FragmentPropertyRegistration5Binding


class PropertyRegistration5 : Fragment() {

    lateinit var binding : FragmentPropertyRegistration5Binding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPropertyRegistration5Binding.inflate(layoutInflater,container,false)
        navController = findNavController()
        binding.apply {
           propertyNext5.setOnClickListener {
               val action = PropertyRegistration5Directions.actionPropertyRegistration5ToPropertyRegistration6()
               navController.navigate(action)
           }

        }
        // Inflate the layout for this fragment
        return binding.root
    }

}