package com.suhail.travo.ui.fragments.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suhail.travo.R
import com.suhail.travo.databinding.FragmentPropertyRegistration6Binding


class PropertyRegistration6 : Fragment() {

    lateinit var binding : FragmentPropertyRegistration6Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPropertyRegistration6Binding.inflate(layoutInflater,container,false)
        return binding.root
    }
}