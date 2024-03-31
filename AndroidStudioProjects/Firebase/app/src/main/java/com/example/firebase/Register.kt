package com.example.firebase

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Register"
        //setTitle(R.string.register);
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        val registerButton: Button = findViewById(R.id.registerButton)
        val loginTextView: TextView = findViewById(R.id.loginTextView)

//        // Setting up ClickableSpan for Login TextView
//        val loginText = getString(R.string.haveAcctAlready)
//        val spannableStringBuilder = SpannableStringBuilder(loginText)
//        val clickableSpan = object : ClickableSpan() {
//            override fun onClick(widget: View) {
//                // Handle click action here
//                val intent = Intent(this@Register, MainActivity::class.java)
//                startActivity(intent)
//            }
//        }
//        val startPosition = loginText.indexOf(getString(R.string.login))
//        spannableStringBuilder.setSpan(clickableSpan, startPosition, startPosition + getString(R.string.login).length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        loginTextView.text = spannableStringBuilder

        registerButton.setOnClickListener {
            // Perform your registration logic here
            // For example, you can start a new activity after registration
            val email = binding.emailEditText.text.toString()
            val pwd = binding.passwordEditText.text.toString()
            if(email.isNotEmpty() && pwd.isNotEmpty())
            {
                MainActivity.auth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }

        }

        loginTextView.setOnClickListener {
            // Perform your registration logic here
            // For example, you can start a new activity after registration
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}
