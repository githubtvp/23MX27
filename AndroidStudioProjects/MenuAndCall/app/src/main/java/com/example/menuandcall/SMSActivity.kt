package com.example.menuandcall

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class SMSActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_smsactivity)
      //  pr("Here in SMSMain")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                systemBars.bottom)
            insets
        }
        if (ContextCompat.checkSelfPermission(this@SMSActivity,
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
        super.onRequestPermissionsResult(requestCode, permissions,
            grantResults)
        if(requestCode==111 && grantResults[0]==
            PackageManager.PERMISSION_GRANTED)
            readmessage()
    }
    private fun readmessage()
    {
        pr("Here in SMSMain")
        val t = findViewById<TextView>(R.id.t)
        var b = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                pr("Here in SMSMain 1")
                for(sms in Telephony.Sms.Intents.getMessagesFromIntent(p1))
                {
                    pr("Here in SMSMain inside 2")
                    t.text = sms.displayMessageBody
                }
            }//End - override fun onReceive(p0: Context?, p1: Intent?)
        }//End - var b = object : BroadcastReceiver()
        registerReceiver(b, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }
    private fun pr(msg : String)
    {
        Toast.makeText(
            this, msg,
            Toast.LENGTH_LONG
        ).show()
    }
}
