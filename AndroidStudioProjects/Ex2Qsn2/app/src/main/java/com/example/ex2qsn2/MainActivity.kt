package com.example.ex2qsn2
//import android.R
import com.example.ex2qsn2.R

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val s1 = findViewById<ImageView>(R.id.imageView1)
        val s2 = findViewById<ImageView>(R.id.imageView2)
        val s3 = findViewById<ImageView>(R.id.imageView3)
        val s4 = findViewById<ImageView>(R.id.imageView4)
        val s5 = findViewById<ImageView>(R.id.imageView5)
        var msg1 = findViewById<TextView>(R.id.textView2)
        val btn1 = findViewById<Button>(R.id.btn1)
        val mlTxt = findViewById<EditText>(R.id.multiLineEditText1)

        msg1.text = " "
        mlTxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // No implementation needed
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // No implementation needed
            }

            override fun afterTextChanged(s: Editable) {
                val words = s.toString().split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray() // Split text into words
                if (words.size > 100) {
                    // If word count exceeds 100, remove excess words
                    val excessWords = words.size - 100
                    val lastIndex = s.toString().lastIndexOf(" ", s.length)
                    if (lastIndex != -1) {
                        s.delete(lastIndex, s.length)
                    }
                }
            }
        })

        s1.setOnClickListener {
            resetStarColors(s1, msg1)
            onStarClk(s1)
            msg1.text = "Disappointed : Very Poor"
        }
        s2.setOnClickListener {
            resetStarColors(s2, msg1)
            onStarClk(s1)
            onStarClk(s2)
            msg1.text = "Not Good : Need improvement"
        }
        s3.setOnClickListener {
            resetStarColors(s3, msg1)
            onStarClk(s1)
            onStarClk(s2)
            onStarClk(s3)
            msg1.text = "Satisfied"
        }
        s4.setOnClickListener {
            resetStarColors(s4, msg1)
            onStarClk(s1)
            onStarClk(s2)
            onStarClk(s3)
            onStarClk(s4)
            msg1.text = "Good : I enjoyed it"
        }
        s5.setOnClickListener {
            resetStarColors(s5, msg1)
            onStarClk(s1)
            onStarClk(s2)
            onStarClk(s3)
            onStarClk(s4)
            onStarClk(s5)
            msg1.text = "Awesome : I love it"
        }
        btn1.setOnClickListener{ onClkBtn1(mlTxt) }

    }

    fun resetStarColors(s: ImageView, msg1: TextView) {
        s.setColorFilter(null)
        msg1.text = ""
    }

    fun onStarClk(s: ImageView) {
        s.setColorFilter(Color.YELLOW)
    }

    fun onClkBtn1(mlTxt : EditText) {
        val i = Intent(this, MainActivity2::class.java)
        val textToPass = mlTxt.text.toString()
        i.putExtra("textToDisplay", textToPass)
        startActivity(i)
    }
}