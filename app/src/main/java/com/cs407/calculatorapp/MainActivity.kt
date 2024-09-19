package com.cs407.calculatorapp

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ensures that system bars insets are handled
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editText1 = findViewById<EditText>(R.id.editTextText)
        val editText2 = findViewById<EditText>(R.id.editTextText2)
        val buttonAdd = findViewById<Button>(R.id.button)
        val buttonSubtract = findViewById<Button>(R.id.button2)
        val buttonMultiply = findViewById<Button>(R.id.button3)
        val buttonDivide = findViewById<Button>(R.id.button4)

        // Ensure only integers are accepted
        editText1.inputType = InputType.TYPE_CLASS_NUMBER
        editText2.inputType = InputType.TYPE_CLASS_NUMBER

        // Addition
        buttonAdd.setOnClickListener {
            handleOperation(editText1, editText2, "add")
        }

        // Subtraction
        buttonSubtract.setOnClickListener {
            handleOperation(editText1, editText2, "subtract")
        }

        // Multiplication
        buttonMultiply.setOnClickListener {
            handleOperation(editText1, editText2, "multiply")
        }

        // Division with divide-by-zero handling
        buttonDivide.setOnClickListener {
            val num1 = editText1.text.toString().toIntOrNull()
            val num2 = editText2.text.toString().toIntOrNull()

            if (num1 == null || num2 == null) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            } else if (num2 == 0) {
                Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
            } else {
                handleOperation(editText1, editText2, "divide")
            }
        }
    }

    private fun handleOperation(editText1: EditText, editText2: EditText, operation: String) {
        val num1 = editText1.text.toString().toIntOrNull()
        val num2 = editText2.text.toString().toIntOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operation) {
            "add" -> num1 + num2
            "subtract" -> num1 - num2
            "multiply" -> num1 * num2
            "divide" -> num1 / num2
            else -> 0
        }

        // Pass result and operation to new activity
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("result", result)
            putExtra("operation", operation)
        }
        startActivity(intent)
    }
}
