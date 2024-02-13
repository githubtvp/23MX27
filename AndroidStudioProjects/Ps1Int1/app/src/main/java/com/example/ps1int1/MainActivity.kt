package com.example.ps1int1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1 = findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener(View.OnClickListener{onClkBtn1()})
        val btn2 = findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener(View.OnClickListener{onClkBtn2()})
        val btn3 = findViewById<Button>(R.id.btn3)
        btn3.setOnClickListener(View.OnClickListener{onClkBtn3()})
    }
    fun onClkBtn1()
    {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse("http://www.psgtech.edu/")
        startActivity(i)
    }
    fun onClkBtn2()
    {
        val i = Intent(this, MainActivity2::class.java)
        startActivity(i)
    }

    fun onClkBtn3()
    {
        val i = Intent(this, MainActivity3::class.java)
        startActivity(i)
    }


}