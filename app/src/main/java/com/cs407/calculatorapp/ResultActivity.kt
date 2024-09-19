package com.cs407.calculatorapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Handle window insets (for devices with notches, etc.)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the result and operation details from the intent
        val result = intent.getIntExtra("result", 0) // Fix the key and type
        val operation = intent.getStringExtra("operation") ?: "Unknown Operation"

        // Reference to the TextView for displaying the result
        val resultTextView: TextView = findViewById(R.id.resultTextView)

        // Set the text in the TextView to display the result using formatted string resource
        val formattedText = getString(R.string.result_text, operation, result)
        resultTextView.text = formattedText
    }
}