package com.example.searchview1

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.searchview1.databinding.ActivityMainBinding
import android.database.Cursor
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    var cols = listOf<String>(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    ).toTypedArray()

    lateinit var binding: ActivityMainBinding
    private lateinit var contactListAdapter: ArrayAdapter<ContactItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the adapter for the ListView
        contactListAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1
        )


        // Query the Contacts content provider to retrieve contact names and phone numbers
        val cursor: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )

        val contactList = mutableListOf<ContactItem>()

        cursor?.use { cursor ->
            while (cursor.moveToNext()) {
                val displayName =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNumber =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                // Add contact name and phone number to the list
                contactList.add(ContactItem(displayName, phoneNumber))
            }
        }

        // Populate the adapter with contact data
        contactListAdapter.addAll(contactList)
        binding.userList.adapter = contactListAdapter

        // Set up SearchView for filtering by name
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                contactListAdapter.filter.filter(newText)
                return false
            }
        })

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                Array(1) { Manifest.permission.READ_CONTACTS }, 111
            )
        } else {
            readContact()
        }
    }

    // Data class to represent a contact item (name and phone number)
    data class ContactItem(val name: String, val phoneNumber: String) {
        override fun toString(): String {
            return name // Display only the name in the ListView
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            readContact()
    }

    private fun readContact() {
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        var userList = findViewById<ListView>(R.id.userList);
        var cr: ContentResolver = contentResolver
        var cursor = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
//        Log.i("Contact", s!!.count.toString())
//        while(s.moveToNext()){
//            var s1:String = s.getString((0))
//            var s2:String = s.getString((1))
//            Log.i("Contacts", s1+s2)
//        }
        val fromCols = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val toViews = intArrayOf(android.R.id.text1, android.R.id.text2)

        var adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            cursor,
            fromCols,
            toViews,
            0)
        userList.adapter = adapter
    }

}