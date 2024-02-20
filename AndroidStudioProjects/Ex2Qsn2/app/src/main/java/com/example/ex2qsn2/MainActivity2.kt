package com.example.ex2qsn2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val txtV1 = findViewById<TextView>(R.id.txtView21)
        val btn1 = findViewById<Button>(R.id.btn1)
        val receivedText = intent.getStringExtra("textToDisplay")
        txtV1.text = receivedText
        btn1.setOnClickListener{ onClkBtn1() }
    }
    fun onClkBtn1() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}