package com.example.labtest1

import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchTextActivity : AppCompatActivity() {
    private lateinit var textViewText: TextView
    private lateinit var editTextSearch: EditText
    private lateinit var paragraph: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_text)

        textViewText = findViewById(R.id.textViewText)
        editTextSearch = findViewById(R.id.editTextSearch)

        paragraph = "   Mobile app development is " +
                "a process for building " +
                "mobile applications that " +
                "run on mobile devices. " +
                "These applications can " +
                "either be pre-installed or " +
                "downloaded and installed " +
                "by the user later. \n  User experience is what " +
                "customers feel when they use " +
                "your products. In our case, the " +
                "design should be such that it " +
                "creates a specific emotional " +
                "response from the customer after " +
                "using the app. User experiences " +
                "include factors like design, " +
                "accessibility, marketing, usability, " +
                "system performance."
        textViewText.text = paragraph
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchText = editTextSearch.text.toString()
                highlightSearchText(searchText)
            }
        })
    }
    private fun highlightSearchText(searchText: String) {
        val spannableStringBuilder = SpannableStringBuilder(paragraph)
        if (searchText.isNotEmpty()) {
            val searchTextLength = searchText.length
            var startIndex = 0
            var index: Int

            while (paragraph.indexOf(searchText, startIndex, ignoreCase = true).also { index = it } != -1) {
                spannableStringBuilder.setSpan(
                    android.text.style.BackgroundColorSpan(android.graphics.Color.GRAY),
                    index,
                    index + searchTextLength,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                startIndex = index + searchTextLength
            }
        }
        textViewText.text = spannableStringBuilder
    }
}
