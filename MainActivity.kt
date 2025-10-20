package com.example.basiccodelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.basiccodelabs.ui.theme.BasicCodelabsTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicCodelabsTheme {
                AppNav()
            }
        }
    }
}

@Composable
fun AppNav() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController,
                onCalculatorClick = {
                    navController.navigate("calculator")
                },
                onNotepadClick = {
                    navController.navigate("notepad")
                }
            )
        }
        composable("calculator") {
            Calculator(navController = navController, onBackClick = { navController.popBackStack() })
        }
        composable("notepad") {
            NotepadScreen(navController = navController, onBackClick = { navController.popBackStack() })
        }
    }
}

@Composable
fun MainScreen(
    navController: NavHostController,
    onCalculatorClick: () -> Unit,
    onNotepadClick: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onCalculatorClick) {
                Text("Calculator")
            }
            Button(onClick = onNotepadClick) {
                Text("Notepad")
            }
        }
    }
}