package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var workingsTV: TextView
    private lateinit var resultsTV: TextView
    private var workings: String = ""
    private var currentNumber: String = ""
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workingsTV = findViewById(R.id.workingsTV)
        resultsTV = findViewById(R.id.resultsTV)
    }

    private fun setWorkings(value: String) {
        workings += value
        workingsTV.text = workings
    }

    fun buttonCE(view: View) {
        workings = ""
        currentNumber = ""
        operator = null
        resultsTV.text = "0"
        workingsTV.text = ""
    }

    fun buttonC(view: View) {
        if (workings.isNotEmpty()) {
            workings = workings.dropLast(1)
            workingsTV.text = workings
        }
    }

    fun buttonBS(view: View) {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            resultsTV.text = currentNumber
        }
    }

    fun numberClick(view: View) {
        val button = view as Button
        val value = button.text.toString()
        currentNumber += value
        resultsTV.text = currentNumber
        setWorkings(value)
    }

    fun operatorClick(view: View) {
        val button = view as Button
        operator = button.text.toString()
        setWorkings(" $operator ")
        currentNumber = ""
    }

    fun equalsAction(view: View) {
        val parts = workings.split(" ")
        if (parts.size == 3) {
            val left = parts[0].toDouble()
            val op = parts[1]
            val right = parts[2].toDouble()

            val result = when (op) {
                "+" -> left + right
                "-" -> left - right
                "x" -> left * right
                "/" -> left / right
                else -> 0.0
            }

            resultsTV.text = result.toString()
            workings = result.toString()
            currentNumber = result.toString()
        }
    }

    fun dotClick(view: View) {
        if (!currentNumber.contains(".")) {
            currentNumber += "."
            resultsTV.text = currentNumber
            setWorkings(".")
        }
    }

    fun plusMinusClick(view: View) {
        if (currentNumber.isNotEmpty()) {
            currentNumber = if (currentNumber.startsWith("-")) {
                currentNumber.drop(1)
            } else {
                "-$currentNumber"
            }
            resultsTV.text = currentNumber
            workings = currentNumber
            workingsTV.text = workings
        }
    }
}
