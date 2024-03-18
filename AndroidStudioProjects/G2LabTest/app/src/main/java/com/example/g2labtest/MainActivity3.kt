package com.example.g2labtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }
    fun start(view: View?){
        startService(Intent(this,MyService::class.java))
    }
    fun stop(view: View?){
        stopService(Intent(this,MyService::class.java))
    }
}
