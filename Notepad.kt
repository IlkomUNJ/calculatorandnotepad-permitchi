package com.example.basiccodelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.copy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.basiccodelabs.ui.theme.BasicCodelabsTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle


@Composable
fun NotepadScreen (navController: NavHostController, onBackClick: () -> Unit) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(AnnotatedString("")))
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier.align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }

                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                    val selection = textFieldValue.selection
                    if (!selection.collapsed) {
                        val newAnnotatedString = buildAnnotatedString {
                            append(textFieldValue.annotatedString)
                            addStyle(
                                style = SpanStyle(fontWeight = FontWeight.Bold),
                                start = selection.start,
                                end = selection.end
                            )
                        }
                        textFieldValue = textFieldValue.copy(annotatedString = newAnnotatedString)
                    }
                }) {
                    Text("Bold")
                }

                Button(onClick = {
                    val selection = textFieldValue.selection
                    if (!selection.collapsed) {
                        val newAnnotatedString = buildAnnotatedString {
                            append(textFieldValue.annotatedString)
                            addStyle(
                                style = SpanStyle(fontStyle = FontStyle.Italic),
                                start = selection.start,
                                end = selection.end
                            )
                        }
                        textFieldValue = textFieldValue.copy(annotatedString = newAnnotatedString)
                    }
                }, modifier = Modifier.padding(start = 8.dp)) {
                    Text("Italic")
                }
            }

            TextField(
                value = textFieldValue,
                onValueChange = { newValue ->
                    // This is the corrected logic
                    textFieldValue = newValue.copy(
                        annotatedString = textFieldValue.annotatedString
                            .copy(text = newValue.text)
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
            )
        }
    }
}


fun AnnotatedString.copy(text: String): AnnotatedString {
    return buildAnnotatedString {
        append(text)
        val original = this@copy
        original.spanStyles.forEach { style ->
            val start = style.start
            val end = style.end
            if (start < text.length && end <= text.length) {
                addStyle(style.item, start, end)
            }
        }
    }
}