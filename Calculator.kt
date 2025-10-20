package com.example.basiccodelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import org.mariuszgromada.math.mxparser.Expression
import com.example.basiccodelabs.ui.theme.BasicCodelabsTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import com.example.basiccodelabs.MainActivity

@Composable
fun Calculator(navController: NavHostController, onBackClick: () -> Unit) {
    var currentInput by remember {mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        IconButton(onClick = onBackClick) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = currentInput.ifEmpty { "0" },
            fontSize = 40.sp,
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        )
        Text(text = "Result", modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp))
        Text(
            text = result.ifEmpty {
                "0"
            },
            fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        //scientific calc
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = "sin($currentInput)"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "sin(x)")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = "cos($currentInput)"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "cos(x)")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = "tan($currentInput)"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "tan(x)")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = "log10($currentInput)"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "log(x)")
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = "asin($currentInput)"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "inv(sin())", fontSize = 8.sp)
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = "acos($currentInput)"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "inv(cos())", fontSize = 8.sp)
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = "atan($currentInput)"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "inv(tan())", fontSize = 8.sp)
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = "ln($currentInput)"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "ln(x)")
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = "1/($currentInput)"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "1/x")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty() && currentInput.last() !in "+-*/%^") {
                        currentInput += "!"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "x!")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty() && currentInput.last() !in "+-*/%^") {
                        currentInput += "^"
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "x^y")
            }
            Button(
                onClick = { currentInput += "√" },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "√")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        //basic Calc
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Button(
                onClick = {
                    currentInput = ""
                    result = ""
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "AC")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = currentInput.dropLast(1) }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "<-")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty() && currentInput.last() !in "+-*/%") {
                        currentInput += "%"
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "%")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty() && currentInput.last() !in "+-*/%") {
                        currentInput += "*"
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "*")
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Button(
                onClick = { currentInput += "7" },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "7")
            }
            Button(
                onClick = { currentInput += "8" },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "8")
            }
            Button(
                onClick = { currentInput += "9" },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "9")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty() && currentInput.last() !in "+-*/%") {
                        currentInput += "/"
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "/")
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Button(
                onClick = { currentInput += "4" },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "4")
            }
            Button(
                onClick = { currentInput += "5" } ,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "5")
            }
            Button(
                onClick = { currentInput += "6" },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "6")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty() && currentInput.last() !in "+-*/%") {
                        currentInput += "-"
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "-")
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Button(
                onClick = { currentInput += "1" },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "1")
            }
            Button(
                onClick = { currentInput += "2" },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "2")
            }
            Button(
                onClick = { currentInput += "3" },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "3")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty() && currentInput.last() !in "+-*/%") {
                        currentInput += "+"
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "+")
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Button(
                onClick = { currentInput += "0" },
                modifier = Modifier.weight(2f)
            ) {
                Text(text = "0")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty() && currentInput.last() !in "+-*/%") {
                        currentInput += "."
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = ".")
            }
            Button(
                onClick = {
                    if (currentInput.isNotEmpty()) {
                        result = evaluateExpression(currentInput)
                        currentInput = "" }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "=")
            }
        }
    }
}
}

fun evaluateExpression(expression: String): String {
    val ex = Expression(expression)

    if (ex.checkSyntax()) {
        val result = ex.calculate()
        return if (result.isNaN()) {
            "Error"
        } else {
            result.toString()
        }
    } else {
        return "Invalid Syntax"
    }
}