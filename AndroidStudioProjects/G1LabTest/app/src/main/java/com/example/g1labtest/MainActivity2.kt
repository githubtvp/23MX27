package com.example.g1labtest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.g1labtest.databinding.ActivityMain2Binding
import com.example.g1labtest.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var adapter: ArrayAdapter<Contact>
    companion object {
        private const val PERMISSION_REQUEST_READ_CONTACTS = 100
        private const val PERMISSION_REQUEST_CALL_PHONE = 101
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ArrayAdapter<Contact>(
            this,
            android.R.layout.simple_list_item_1,
            mutableListOf()
        )

        binding.listView.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { adapter.filter.filter(it) }
                return true
            }
        })

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            loadContacts()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSION_REQUEST_READ_CONTACTS
            )
        }

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val contact = adapter.getItem(position)
            contact?.let {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CALL_PHONE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    makePhoneCall(it.phoneNumber)
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        PERMISSION_REQUEST_CALL_PHONE
                    )
                }
            }
        }

    }//End-override fun onCreate(savedInstanceState: Bundle?)

    private fun loadContacts() {
        val contentResolver = applicationContext.contentResolver
        val projection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
        )

        val selection = null
        val selectionArgs = null
        val sortOrder: String? = "${ContactsContract.Contacts.DISPLAY_NAME} ASC"

        contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
            val nameColumn =
                cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val id = cursor.getString(idColumn)
                val name = cursor.getString(nameColumn)

                val projection = null // => retrieve all colns
                val selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                val selectionArgs = arrayOf(id)
                val sortOrder = null

                val phoneCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    projection,
                    selection,
                    selectionArgs,
                    sortOrder
                )

                phoneCursor?.use { pcursor ->
                    val phoneNumberColumn =
                        pcursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)

                    while (pcursor.moveToNext()) {
                        val phoneNumber = pcursor.getString(phoneNumberColumn)
                        if (!phoneNumber.isNullOrEmpty()) {
                            adapter.add(Contact(name, phoneNumber))
                        }
                    }
                }
            }
        }
        updateUI()
    }//End - private fun loadContacts()

    private fun updateUI() {
        if (adapter.count == 0) {
            binding.listView.visibility = ListView.GONE
            findViewById<TextView>(R.id.emptyView).visibility = TextView.VISIBLE
        } else {
            binding.listView.visibility = ListView.VISIBLE
            findViewById<TextView>(R.id.emptyView).visibility = TextView.GONE
        }
    }//End - private fun updateUI()

    private fun makePhoneCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }//End - private fun makePhoneCall

}//End - class MainActivity : AppCompatActivity()
