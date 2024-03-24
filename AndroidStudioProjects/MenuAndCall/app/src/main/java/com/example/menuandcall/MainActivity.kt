package com.example.menuandcall

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
class MainActivity : AppCompatActivity() {
    val c = Intent(Intent.ACTION_CALL)
    private lateinit var t: TextView
    private var telNo = "tel:+91-9498413977"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  c.data = Uri.parse("tel:+91-9498413977" )
        c.data = Uri.parse(telNo)
        t= findViewById(R.id.t)
        registerForContextMenu(t)//registers t for context menu
    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.main_menu,menu)
        menu?.setHeaderTitle("Select")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val i:Int = item.itemId
        when(i)
        {
            R.id.Call ->
            {
                startCall()
            }
            R.id.SMS ->
            {
                SMS()
            }
            R.id.WhatsApp ->
            {
                whatsapp()
            }
        }
        return super.onContextItemSelected(item)
    }
    private fun startCall() {
        if (ContextCompat.checkSelfPermission(this@MainActivity,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                Array(1){Manifest.permission.CALL_PHONE},111)
        } else {
            startActivity(c)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions,
            grantResults)
        if(requestCode==111 &&
            grantResults[0]==PackageManager.PERMISSION_GRANTED)
            startActivity(c)
    }

    private fun SMS()
    {
        val intent = Intent(this, SMSActivity::class.java)
        startActivity(intent)
    }


    private fun whatsapp() {
        val installed: Boolean = appInstalledOrNot("com.whatsapp")
        if(installed)
        {
            val intent = Intent(Intent.ACTION_VIEW)
           // intent.setData(Uri.parse("https://api.whatsapp.com/send?phone="+"+919362688752" + "&text=Hai!"));
            intent.setData(Uri.parse("https://api.whatsapp.com/send?phone="+ telNo + "&text=Hi! from Tvp"));
                    startActivity(intent)
        }
        else
        {
            Toast.makeText(applicationContext,"Not installed",Toast.LENGTH_LONG).show()
        }
    }
    private fun appInstalledOrNot(url: String): Boolean {
        val packageManager = packageManager
        val app_installed: Boolean
        app_installed = try {
            packageManager.getPackageInfo(url,
                PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
        return app_installed
    }

}
