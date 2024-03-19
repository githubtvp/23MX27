package com.example.g1labtest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.g1labtest.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {

    private lateinit var binding: ActivityMain4Binding
    private var phNoArr = arrayOf("8296077914", "12345678")
    private var captchaArr = arrayOf("abcd", "efgh")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignin.isEnabled = false
        initUI()
    }

    private fun initUI() {

        binding.phoneno.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do something before text changed
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Do something when text is changing
                val usernameInput = s.toString()
                if (!usernameInput.isEmpty()) {
                    binding.captcha.isEnabled = true
                } else {
                    binding.captcha.isEnabled = false
                    binding.btnSignin.isEnabled = false
                }
            }
            override fun afterTextChanged(s: Editable) {
                // Do something after text changed
            }
        })

        binding.captcha.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do something before text changed
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Do something when text is changing
                val passwordInput = s.toString()
                if (!passwordInput.isEmpty() && !binding.phoneno.text.toString().isEmpty()) {
                    binding.btnSignin.isEnabled = true
                    binding.btnSignin.setOnClickListener { onClickBtnLogIn() }
                } else {
                    binding.btnSignin.isEnabled = false
                }
            }
            override fun afterTextChanged(s: Editable) {
                // Do something after text changed
            }
        })
    }

    fun verified(phNo: String, cap: String): Boolean {
        var foundPhNo : Boolean
        if(phNo.length != 10)
        {
            foundPhNo = false
            pr("$phNo length is not 10 digits.")
            return foundPhNo
        }

        if (phNo.length == 10 && phNo.contains(phNo) && captchaArr.contains(cap)) {
             foundPhNo = true
         //   pr("$phNo and $cap exist in the list.")
        } else {
            foundPhNo = false
         //  pr("$phNo or $cap does not exist in the list.")
        }
        return foundPhNo
    }

    fun onClickBtnLogIn()
    {
        if (binding.phoneno.text.toString().isNotBlank() && binding.captcha.text.toString()
                .isNotBlank()
        ) {
            if (verified(binding.phoneno.text.toString(), binding.captcha.text.toString())) {
                pr("Success!")
                binding.btnSignin.isEnabled = false
            }
            else
            {
                pr("Failure XXX!")
            }
        }
    }
    fun pr(msg: String) {
        Toast.makeText(this, "Login Page : " + msg, Toast.LENGTH_LONG).show()
    }
}