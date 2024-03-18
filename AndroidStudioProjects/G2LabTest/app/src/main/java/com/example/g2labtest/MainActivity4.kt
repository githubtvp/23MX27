package com.example.g2labtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast


class MainActivity4 : AppCompatActivity() {
    lateinit var text : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        var ratingBar = findViewById<RatingBar>(R.id.ratingBar)

        ratingBar.setOnRatingBarChangeListener{_, rating, _ ->
            val ratingInt = rating.toInt()

            when(ratingInt){
                1 -> text = "Disappointed. Very Poor"
                2 -> text = "Not good"
                3 -> text = "Satisfied"
                4 -> text = "Good"
                5 -> text = "Awesome"
                else -> text=""
            }
        }
        val button = findViewById<Button>(R.id.onsubmit)
        val editText = findViewById<EditText>(R.id.nametext)
        val name = editText.text
        button.setOnClickListener(View.OnClickListener{
            Toast.makeText(applicationContext, "name $name Rating $text",Toast.LENGTH_LONG).show()
        })
    }
}
