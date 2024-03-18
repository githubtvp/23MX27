package com.example.g2labtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var menuText = findViewById<TextView>(R.id.menutext)
        registerForContextMenu(menuText)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menuInflater.inflate(R.menu.main_menu,menu)
        menu?.setHeaderTitle("Select")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val i : Int = item.itemId
        when(i)
        {
            R.id.ViewContact ->
            {
                viewContact()
            }
            R.id.Playmusic ->
            {
                playMusic()
            }
            R.id.GiveFeeback ->
            {
                giveFeeback()
            }
        }

        return super.onContextItemSelected(item)
    }

    private fun giveFeeback() {
        val i = Intent(this,MainActivity4::class.java)
        startActivity(i)
    }

    private fun playMusic() {
        val i = Intent(this, MainActivity3::class.java)
        startActivity(i)
    }

    private fun viewContact() {
        val i = Intent(this , MainActivity2::class.java)
        startActivity(i)
    }
}
