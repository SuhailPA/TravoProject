package com.suhail.travo.ui.homeScreen.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.suhail.travo.R
import com.suhail.travo.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    lateinit var binding : FragmentProfileBinding
    val viewModel : ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        binding.lifecycleOwner = viewLifecycleOwner
        binding.profileViewModel = viewModel
        viewModel.userDetails_.observe(viewLifecycleOwner, Observer {
            Log.i("userDetails87678",it.user_name!!)
        })
        return binding.root
    }
}