package com.example.widgets_controls

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.widgets_controls.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedText: String? = null // Store the selected text
    var arr = arrayOf("MCA", "MBA", "ME", "M.SC", "BE", "BTech", "B.Sc")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.isEnabled = false
        //declare an array adapter with its associated array
        val arrAdap: ArrayAdapter<*> =
            ArrayAdapter(this, android.R.layout.simple_selectable_list_item, arr)
       //attach the adapter to a listview or gridview type display on screen viewer
        binding.lv1.adapter = arrAdap

        //set up an onItemClick.... event listener on the listview
        binding.lv1.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                selectedText = arr[position] // Store the selected item text
                binding.btnStart.isEnabled = true
            }
    }

    fun start(view: View?) {
        // Check if an item is selected
        if (selectedText != null) {
            // Create an Intent to start the next activity
            val nextScreen = Intent(this, MainActivity2::class.java)

            // Put the selected item text as an extra in the Intent
            nextScreen.putExtra("textToDisplay", selectedText)

            // Start the next activity
            startActivity(nextScreen)
            binding.btnStart.isEnabled = false
        } else {
            // Display a toast indicating that no item is selected
            pr("Please select an item")
        }

    }

    fun pr(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }
}