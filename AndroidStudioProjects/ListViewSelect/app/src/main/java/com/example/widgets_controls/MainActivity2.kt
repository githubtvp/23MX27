package com.example.widgets_controls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.widgets_controls.databinding.ActivityMain2Binding
import com.example.widgets_controls.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main2)

        val recdText = intent.getStringExtra("textToDisplay")
        binding.textView1.text = recdText
    }

    fun start(view: View?)
    {
        val nextScreen = Intent(this, MainActivity::class.java)

        // Put the selected item text as an extra in the Intent
       // nextScreen.putExtra("textToDisplay", selectedText)

        // Start the next activity
        startActivity(nextScreen)
    }
    fun pr(msg : String)
    {
        Toast.makeText(applicationContext,msg, Toast.LENGTH_LONG).show()
    }
}

