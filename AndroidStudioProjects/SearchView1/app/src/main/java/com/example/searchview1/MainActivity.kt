package com.example.searchview1

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.searchview1.databinding.ActivityMainBinding
import android.annotation.SuppressLint
class MainActivity : AppCompatActivity() {

    
    private val contRes1: ContentResolver = this.contentResolver
    private lateinit var adapter: ArrayAdapter<Contact>
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArrayAdapter<Contact>(
            this,
            android.R.layout.simple_list_item_1,
            mutableListOf()
        )
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val contact = adapter.getItem(position)
            contact?.let {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${it.phoneNo}"))
                startActivity(intent)
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { adapter.filter.filter(it) }
                return true
            }
        })
        loadContacts()
    }//End -- override fun onCreate(savedInstanceState: Bundle?)

    @SuppressLint("Range")
    private fun loadContacts() {
       // val contRes: ContentResolver = this.contentResolver
        val cur = contRes1.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
        cur?.use { cur ->
            while (cur.moveToNext()) {
                val id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phCur = contRes1.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?",
                    arrayOf(id),
                    null
                )
                phCur?.use { phCur ->
                    while (phCur.moveToNext()) {
                        val phNo =
                            phCur.getString(phCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        if (phNo.isNotEmpty()) {
                            val contact = Contact(name, phNo)
                            adapter.add(contact)
                        }
                    }
                }
            }
        }
    }
}//End-class MainActivity : AppCompatActivity()
