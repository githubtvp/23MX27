package com.example.ringtone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState:
                          Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    // Start the service
    fun start(view: View?) {
        startService(Intent(this,
            MyService::class.java))
    }
    // Stop the service
    fun stop(view: View?) {
        stopService(Intent(this,
            MyService::class.java))
    }
}