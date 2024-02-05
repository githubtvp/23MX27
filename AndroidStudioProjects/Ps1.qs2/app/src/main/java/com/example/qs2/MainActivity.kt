package com.example.qs2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn1)
        btn.setOnClickListener({ onBtnClk(btn)})
    }

    fun onBtnClk(btn : Button)
    {
        //btn.text = "Done Calculations"
        val txtIp1 = findViewById<TextView>(R.id.txtIp1)
        val txtIp2 = findViewById<TextView>(R.id.txtIp2)
        var ip1Str: String = txtIp1.getText().toString().trim()
        var ip2Str: String = txtIp2.getText().toString().trim()
        if (!ip1Str.isEmpty()) {
            // Input is not blank. Now, try to parse it as an integer
            try {
                val f: Int = ip1Str.toInt()
                // If parsing is successful, it's an integer
                // Your logic for handling the integer value goes here
                FahrToCel(f, txtIp2, btn)
            } catch (e: NumberFormatException) {
                // If parsing fails, it's not an integer
                // Handle the case where it's not an integer
            }
        } else {
            // Input is blank
            // Handle the case where the input is blank
        }
        if (!ip2Str.isEmpty()) {
            // Input is not blank. Now, try to parse it as an integer
            try {
                val c: Int = ip2Str.toInt()
                // If parsing is successful, it's an integer
                // Your logic for handling the integer value goes here
                CelToFahr(c, txtIp1, btn)
            } catch (e: NumberFormatException) {
                // If parsing fails, it's not an integer
                // Handle the case where it's not an integer
            }
        } else {
            // Input is blank
            // Handle the case where the input is blank
        }
    }

    fun FahrToCel(f : Int, txtV: TextView, btn :Button)
    {
        var c = (f - 32)*5/9
        txtV.text = c.toString()
        btn.text = "Done : Fahrenhite to Celsius"
    }
     fun CelToFahr(c : Int, txtV: TextView, btn :Button)
     {
         var f = (c * 9 / 5) + 32
         txtV.text = f.toString()
         btn.text = "Done : Celsius to Fahrenhite"
     }
}