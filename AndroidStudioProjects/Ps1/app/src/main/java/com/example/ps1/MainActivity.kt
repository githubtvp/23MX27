package com.example.ps1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewSwitcher.ViewFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val x = findViewById<TextView>(R.id.t1)
        val z = findViewById<Button>(R.id.b1)
        val y = findViewById<Button>(R.id.b2)
        val w = findViewById<ImageView>(R.id.iv1)
        z.setOnClickListener( View.OnClickListener { x.text = "PSG Tech" } )
        y.setOnClickListener{ w.setImageResource(R.drawable.smiley2)  }
    }

    fun btn1Clk()
    {

    }
}