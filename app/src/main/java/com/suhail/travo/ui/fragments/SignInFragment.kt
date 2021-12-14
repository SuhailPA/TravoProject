package com.suhail.travo.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suhail.travo.R
import com.suhail.travo.databinding.FragmentSignInBinding
import com.suhail.travo.ui.HomeActivity


class SignInFragment : Fragment() {
    lateinit var binding : FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater,container,false)

        binding.signInButton.setOnClickListener {
            var intent = Intent(requireContext(),HomeActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }


}