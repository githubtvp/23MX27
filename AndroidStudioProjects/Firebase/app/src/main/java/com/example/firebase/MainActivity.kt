package com.example.firebase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object{
        lateinit var auth : FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signIn.setOnClickListener {
            // Perform your registration logic here
            // For example, you can start a new activity after registration
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }
        binding.signOut.setOnClickListener {
            auth.signOut()
            binding.userDetails.text = updateData()
        }
    }

    override fun onResume()
    {
        super.onResume()
        binding.userDetails.text = updateData()
    }

    private fun updateData() : String{
        return "Email : ${auth.currentUser?.email}"
    }
}