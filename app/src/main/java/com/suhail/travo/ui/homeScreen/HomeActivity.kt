package com.suhail.travo.ui.homeScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suhail.travo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}