package com.suhail.travo.ui.fragments.property_room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suhail.travo.R
import com.suhail.travo.databinding.FragmentPropertyRoom1Binding


class PropertyRoom1 : Fragment() {

    lateinit var binding : FragmentPropertyRoom1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPropertyRoom1Binding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

}