package com.example.ps1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val x = findViewById<TextView>(R.id.lbl1)
        val z = findViewById<Button>(R.id.btn1)
        val w = findViewById<ImageView>(R.id.img1)
        var n = 1;
        z.setOnClickListener( View.OnClickListener { x.text = "   I am so full  "
            z.text = "Done"
            w.setImageResource(R.drawable.smiley2) } )
    }

    fun btn1Clk(n : Int)
    {

    }
}