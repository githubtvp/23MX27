package com.example.bindingexample


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//required line 1 - import com.example.bindingexample.databinding.ActivityMainBinding
// in build.gradle.kts file include  buildFeatures{
//        viewBinding {enable = true}
//    }

import com.example.bindingexample.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // setContentView(R.layout.activity_main)
    }
}