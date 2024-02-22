package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.Stack

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText1 = findViewById<EditText>(R.id.editText1)
        val btns = listOf<Button>(
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9),
            findViewById(R.id.btn10),
            findViewById(R.id.btn11),
            findViewById(R.id.btn12),
            findViewById(R.id.btn13),
            findViewById(R.id.btn14),
            findViewById(R.id.btn15),
            findViewById(R.id.btn16),
            findViewById(R.id.btn17)
        )

        for (btn in btns) {
            btn.setOnClickListener {
                val btnTxt = btn.getText().toString()
                var dataCalc = " "; // = editText1.getText().toString() + btnTxt
                // editText1.setText(dataCalc)
                if (btnTxt == "C") {
                    clearEntry(editText1)
                } else {
                    dataCalc = editText1.getText().toString() + btnTxt
                    editText1.setText(dataCalc)
                }
                if (btnTxt == "=") {
                    clearEntry(editText1)
                    dataCalc = editText1.getText().toString()
                    var result = evaluateExpression(dataCalc)
                    editText1.setText(result.toString())
                }
            }
        }
    }
}

fun evaluateExpression(expression: String): Double {
    val tokens = tokenize(expression)
    val postfix = infixToPostfix(tokens)
    return evaluatePostfix(postfix)
}

fun tokenize(expression: String): List<String> {
    // Split the expression into tokens (numbers, operators, etc.)
    return expression.split(" ")
}

fun infixToPostfix(infix: List<String>): List<String> {
    val output = mutableListOf<String>()
    val stack = mutableListOf<String>()

    for (token in infix) {
        when {
            token.isNumeric() -> output.add(token)
            token == "(" -> stack.add(token)
            token == ")" -> {
                while (stack.isNotEmpty() && stack.last() != "(") {
                    output.add(stack.removeAt(stack.size - 1))
                }
                stack.removeAt(stack.size - 1) // Remove '('
            }
            else -> {
                while (stack.isNotEmpty() && precedence(token) <= precedence(stack.last())) {
                    output.add(stack.removeAt(stack.size - 1))
                }
                stack.add(token)
            }
        }
    }

    while (stack.isNotEmpty()) {
        output.add(stack.removeAt(stack.size - 1))
    }

    return output
}

fun precedence(operator: String): Int {
    return when (operator) {
        "+", "-" -> 1
        "*", "/" -> 2
        else -> 0
    }
}
fun precedence(operator: Char): Int {
    return when (operator) {
        '+', '-' -> 1
        '*', '/' -> 2
        else -> 0
    }
}

fun evaluatePostfix(postfix: List<String>): Double {
    // Evaluate the postfix expression using a stack-based algorithm
    // You need to implement a stack-based algorithm to evaluate the postfix expression
    // For simplicity, let's assume the expression contains only two operands and one operator
    val stack = Stack<Double>()
    for (token in postfix) {
        if (token.isNumeric()) {
            stack.push(token.toDouble())
        } else {
            val operand2 = stack.pop()
            val operand1 = stack.pop()
            when (token) {
                "+" -> stack.push(operand1 + operand2)
                "-" -> stack.push(operand1 - operand2)
                "*" -> stack.push(operand1 * operand2)
                "/" -> stack.push(operand1 / operand2)
                else -> throw IllegalArgumentException("Invalid operator")
            }
        }
    }
    return stack.pop()
}

fun String.isNumeric(): Boolean {
    return try {
        this.toDouble()
        true
    } catch (e: NumberFormatException) {
        false
    }
}

private fun Double.isWholeNumber(): Boolean {
    return this % 1 == 0.0
}

fun clearEntry(edtTxt: EditText) {
    var str = edtTxt.getText().toString()
    str = str.substring(0, str.length - 1)
    edtTxt.setText(str);
}

