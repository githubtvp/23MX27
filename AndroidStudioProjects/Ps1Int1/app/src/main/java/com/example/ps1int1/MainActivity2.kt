package com.example.ps1int1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val txtV2 = findViewById<TextView>(R.id.txtV2)
        txtV2.setOnClickListener(View.OnClickListener {
            val snkBr = Snackbar.make(it, "This is a simple snack bar", Snackbar.LENGTH_LONG)
            snkBr.setAction("DISMISS",View.OnClickListener { txtV2.text = "Hi" })
            snkBr.show()
        })

        txtV2.setOnLongClickListener(View.OnLongClickListener {
            txtV2.textSize = 30F
            true
        })

        val txtV3 = findViewById<TextView>(R.id.txtV3)
        txtV3.setOnTouchListener(View.OnTouchListener { v, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    txtV3.textSize = 30f
                }

                MotionEvent.ACTION_MOVE -> {
                    txtV3.textSize = 60f
                }

                MotionEvent.ACTION_UP -> {
                    txtV3.textSize = 90f
                }

                MotionEvent.ACTION_CANCEL -> {

                }
                else -> {

                }
            }
                true
        }  )

    }
}