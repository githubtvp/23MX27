package com.example.qs2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtV4 = findViewById<TextView>(R.id.txtV4)
        txtV4.visibility = View.GONE
        val btn = findViewById<Button>(R.id.btn1)
        btn.setOnClickListener({ onBtnClk(btn) })
    }

    fun onBtnClk(btn: Button) {
        //btn.text = "Done Calculations"
        val edTxt1 = findViewById<TextView>(R.id.edTxt1)
        val edTxt2 = findViewById<TextView>(R.id.edTxt2)
        var ip1Str: String = edTxt1.getText().toString().trim()
        var ip2Str: String = edTxt2.getText().toString().trim()
        val txtV4 = findViewById<TextView>(R.id.txtV4)
        txtV4.visibility = View.GONE
        if (!ip1Str.isEmpty()) {
            // Input not blank, parse it as an double
            try {
                val f: Double = ip1Str.toDouble()
                FahrToCel(f, edTxt2, btn)
            } catch (e: NumberFormatException) {
            }
        } else {
            // Handle the case where the input is blank
        }
        if (!ip2Str.isEmpty()) {
            // Input not blank, parse it as an double
            try {
                val c: Double = ip2Str.toDouble()
                CelToFahr(c, edTxt1, btn)
            } catch (e: NumberFormatException) {
                // Handle the case the input is blank
            }
        } else if ( ip1Str.isEmpty()  && ip2Str.isEmpty()  ){
            var msg = "Please enter some suitable values to convert"
            fun1(msg)
          //  btn.setOnClickListener({ onBtnClk(btn) })// Handle the case where the input is blank
        }
    }

    fun FahrToCel(f: Double, txtV: TextView, btn: Button) {
        var c = (f - 32) * 5 / 9
        var cRd = String.format("%.2f", c)
        txtV.text = cRd
        btn.text = "Done : Fahrenheit to Celsius"
        var msg = "$f fahrenheit is $cRd celsius"
        fun1(msg)
    }

    fun CelToFahr(c: Double, txtV: TextView, btn: Button) {
        var f = (c * 9 / 5) + 32
        var fRd = String.format("%.2f", f)
        txtV.text = fRd
        btn.text = "Done : Celsius to Fahrenheit"
        var msg = "$c celsius is $fRd fahrenheit"
        fun1(msg)
    }

    fun fun1(str: String) {
        val txtV4 = findViewById<TextView>(R.id.txtV4)
        txtV4.visibility = View.VISIBLE
        txtV4.text = str
        Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
    }
}