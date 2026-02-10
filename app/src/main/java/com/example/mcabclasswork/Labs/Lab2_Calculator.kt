package com.example.mcabclasswork.Labs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Stack

@Composable
fun Lab2(){
    var displayText by remember { mutableStateOf("0") }
    var expression by remember { mutableStateOf("") }
    var isNewInput by remember { mutableStateOf(true) }

    fun onNumberClick(number: String) {
        if (isNewInput || displayText == "0") {
            displayText = number
            isNewInput = false
        } else {
            displayText += number
        }
        expression += number
    }

    fun onOperationClick(operation: String) {
        if (expression.isNotEmpty() && expression.last().isDigit()) {
            expression += " $operation "
            isNewInput = true
        } else if (expression.isNotEmpty() && expression.endsWith(" ")) {
            // Replace last operator
            expression = expression.dropLast(3) + " $operation "
        }
    }

    fun onEqualClick() {
        if (expression.isNotEmpty()) {
            try {
                val result = evaluateBODMAS(expression)
                displayText = if (result % 1 == 0.0) result.toInt().toString() else result.toString()
                expression = "" // Erase from placeholder as requested
                isNewInput = true
            } catch (e: Exception) {
                displayText = "Error"
                expression = ""
                isNewInput = true
            }
        }
    }

    fun onClearClick() {
        displayText = "0"
        expression = ""
        isNewInput = true
    }

    //UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        // Placeholder for the sequence of operations
        Text(
            text = expression,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = displayText,
            style = MaterialTheme.typography.displayLarge,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        val buttons = listOf(
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "*"),
            listOf("1", "2", "3", "-"),
            listOf("C", "0", "=", "+")
        )

        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { label ->
                    Button(
                        onClick = {
                            when (label) {
                                in "0".."9" -> onNumberClick(label)
                                in listOf("+", "-", "*", "/") -> onOperationClick(label)
                                "=" -> onEqualClick()
                                "C" -> onClearClick()
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                    ) {
                        Text(text = label, fontSize = 24.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

/**
 * Evaluates a mathematical expression string using BODMAS rules.
 */
fun evaluateBODMAS(expression: String): Double {
    val tokens = expression.trim().split(" ")
    if (tokens.isEmpty()) return 0.0

    val values = Stack<Double>()
    val ops = Stack<String>()

    var i = 0
    while (i < tokens.size) {
        val token = tokens[i]

        when {
            token.toDoubleOrNull() != null -> {
                values.push(token.toDouble())
            }
            token in listOf("+", "-", "*", "/") -> {
                while (ops.isNotEmpty() && hasPrecedence(token, ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))
                }
                ops.push(token)
            }
        }
        i++
    }

    while (ops.isNotEmpty()) {
        values.push(applyOp(ops.pop(), values.pop(), values.pop()))
    }

    return values.pop()
}

fun hasPrecedence(op1: String, op2: String): Boolean {
    if ((op1 == "*" || op1 == "/") && (op2 == "+" || op2 == "-")) return false
    return true
}

fun applyOp(op: String, b: Double, a: Double): Double {
    return when (op) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> if (b != 0.0) a / b else 0.0
        else -> 0.0
    }
}
