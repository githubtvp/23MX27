package com.example.contentproviderexample

import android.Manifest
import android.content.ContentResolver
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    var cols = listOf<String>(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID).toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, Array(1){Manifest.permission.READ_CONTACTS}, 111)
        }
        else
        {
            readContact()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            readContact()
    }

    private fun readContact()
    {
        var from = listOf<String>(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID).toTypedArray()
        var to = intArrayOf(android.R.id.text1, android.R.id.text2);
        var lv1 = findViewById<ListView>(R.id.lv1);
        var c:ContentResolver = contentResolver
        var s= c.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, cols,
            null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        Log.i("Contact", s!!.count.toString())
        while(s.moveToNext()){
            var s1:String = s.getString((0))
            var s2:String = s.getString((1))
            Log.i("Contacts", s1+s2)
        }
        var a = SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,s,from,to,0)
        lv1.adapter = a
    }
}