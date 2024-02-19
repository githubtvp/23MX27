package com.example.ex2qsn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val txtV1 = findViewById<TextView>(R.id.txtView21)
        val receivedText = intent.getStringExtra("textToDisplay")
        txtV1.text = receivedText
    }
}