package com.example.ps1qsn1

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1 = findViewById<Button>(R.id.btn1)
        val imgV1 = findViewById<ImageView>(R.id.imgV1)
        val txtV1 =findViewById<TextView>(R.id.txtV1)
        btn1.setOnClickListener({onBtnClick(imgV1, btn1)})
    }
    fun onBtnClick(imgV : ImageView, btn : Button)
    {
        btn.text = "I am so full"
        imgV.setImageResource(R.drawable.smiley2)
    }
}