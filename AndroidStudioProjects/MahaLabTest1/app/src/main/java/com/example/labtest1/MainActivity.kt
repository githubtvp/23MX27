package com.example.labtest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtName = findViewById<TextView>(R.id.txtName)
        registerForContextMenu(txtName)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_dashboard, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val intent = Intent()

        when (item.itemId) {
            R.id.feedback -> {
                intent.setClass(MainActivity@ this, FeedbackActivity::class.java)
            }

            R.id.gallery -> {
                intent.setClass(MainActivity@ this, GalleryActivity::class.java)
            }

            R.id.search_text -> {
                intent.setClass(MainActivity@ this, SearchTextActivity::class.java)
            }
        }
        startActivity(intent)
        return true
    }
}
