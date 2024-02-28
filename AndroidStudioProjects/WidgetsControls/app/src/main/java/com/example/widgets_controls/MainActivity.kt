package com.example.widgets_controls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var array = arrayOf("MCA","MBA","ME","M.SC","BE","BTech","B.Sc")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val l:ListView = findViewById<ListView>(R.id.l1)
        val arrayAdapter : ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_selectable_list_item/*simple_list_item_checked*/, array)
        l.adapter = arrayAdapter
        l.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val i = l.getItemAtPosition(position) as String
            Toast.makeText(applicationContext,"Position : $position\nItem Value:$i", Toast.LENGTH_LONG).show()
        }
    }
    fun start(view: View?) {
        val nextScreen = Intent(this,MainActivity2::class.java)
        startActivity(nextScreen)
   }
}