package com.example.labtest1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val editTextName: EditText = findViewById(R.id.editTextName)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        val btnSubmitFeedback: Button = findViewById(R.id.btnSubmitFeedback)

        btnSubmitFeedback.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val rating = ratingBar.rating.toInt()

            if (name.isNotEmpty() && name.matches(Regex("^[a-zA-Z ]+\$"))) {
                val feedbackMessage = "Thank you, $name, for your feedback!\nRating (out of 5): $rating"
                Toast.makeText(this, feedbackMessage, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
