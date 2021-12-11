package com.suhail.travo.ui.fragments.registration.regitration_screen_one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.suhail.travo.R
import com.suhail.travo.databinding.FragmentPropertyRegistration1Binding


class PropertyRegistrationFirst : Fragment() {

    lateinit var binding : FragmentPropertyRegistration1Binding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPropertyRegistration1Binding.inflate(layoutInflater,container,false)

        navController = findNavController()
        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.menu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.apply {
            propertyNext1.setOnClickListener {
                val action = PropertyRegistrationFirstDirections.actionPropertyRegistrationFirstToPropertyRegistration2()
                navController.navigate(action)
            }
        }
        return binding.root
    }

}