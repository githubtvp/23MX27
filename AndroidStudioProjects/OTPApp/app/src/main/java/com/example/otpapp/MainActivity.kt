package com.example.otpapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest
import android.content.pm.PackageManager
import android.provider.Telephony
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class otp_login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (ContextCompat.checkSelfPermission(this@otp_login,
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(this,
                Array(1){ Manifest.permission.RECEIVE_SMS},111)

        } else {
            readmessage()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode==111 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            readmessage()
    }

    private fun readmessage() {
        val t = findViewById<EditText>(R.id.editext)
        val b = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    val messageBody = sms.messageBody
                    val otpPattern = Regex("Your OTP to login is (\\d+)") // Regex pattern to extract OTP
                    val matchResult = otpPattern.find(messageBody)
                    matchResult?.let {
                        val otp = it.groupValues[1] // Extract the OTP from the match
                        t.setText(otp) // Auto-fill the OTP into the EditText
                        // Optionally, perform submission logic here
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show() // Display "Success" message
                        unregisterReceiver(this) // Unregister the receiver
                    }
                }
            }
        }
        registerReceiver(b, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }

}


