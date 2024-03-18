package com.example.g2labtest

import android.content.ContentResolver
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*

class MainActivity2 : AppCompatActivity() {
    var column = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    ).toTypedArray()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        if(checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, Array(1)
            {
                android.Manifest.permission.READ_CONTACTS
            },111)
        } else {
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
    private fun readContact() {
        var from = listOf<String>(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()
        var to = intArrayOf(android.R.id.text1, android.R.id.text2)
        var list = findViewById<ListView>(R.id.ContactLists)
        var c: ContentResolver = contentResolver
        var s = c.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            column,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
        Log.i("Contacts" , s!!.count.toString())
        while(s.moveToNext()){
            var s1:String = s.getString(0)
            var s2:String = s.getString(1)
            Log.i("Contacts",s1+s2)
        }
        val a = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            s,
            from,
            to,
            0
        )
        list.adapter = a

        list.setOnItemClickListener { _, _, position, _ ->
            if(s.moveToPosition(position)){
                val phoneNumberIndex = s.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                if(phoneNumberIndex != -1){
                    val phoneNumber = s.getString(phoneNumberIndex)
                    makePhoneCall(phoneNumber)
                } else {
                    Log.e("read","Phone number column not found")
                }
            } else {
                Log.e("ReadContacts","Failed to move cursor to position $position")
            }
        }
    }

    private fun makePhoneCall(phoneNumber: String?) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel: $phoneNumber")
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED)
        {
            startActivity(callIntent)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                111
            )
        }
    }
}
