package com.example.widgets_controls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    var array= arrayOf("India",
    "USA","Austria","Australia","UK","Italy","Ireland","Africa")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var arrayAdapter: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, array)
        var m:AutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.ac)
        m.setAdapter(arrayAdapter)
        m.threshold = 1
        m.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val x = parent.getItemAtPosition(position).toString()
            Toast.makeText(applicationContext, x, Toast.LENGTH_LONG).show()
        }
    }
}

