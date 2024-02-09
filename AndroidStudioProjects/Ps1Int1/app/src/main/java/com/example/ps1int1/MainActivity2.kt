package com.example.ps1int1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val txtV2 = findViewById<TextView>(R.id.txtV2)
        txtV2.setOnClickListener(View.OnClickListener {


        })

        txtV2.setOnLongClickListener(View.OnLongClickListener {
            txtV2.textSize = 30F
            true
        })

        val txtV3 = findViewById<TextView>(R.id.txtV3)

    }
}