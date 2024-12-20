package com.example.quotegeneratorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.quotegeneratorapp.screens.HomeScreen
import com.example.quotegeneratorapp.ui.theme.QuoteGeneratorAppTheme
import com.example.quotegeneratorapp.viewmodel.HomeViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuoteGeneratorAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBarItems()
                    },


                ) { innerPadding ->

                    val homeViewmodel: HomeViewmodel by viewModels()
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = homeViewmodel
                    )
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun TopAppBarItems() {
        TopAppBar(
            title = { Text("Quote Generator") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Cyan,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black,
                actionIconContentColor = Color.Cyan
            ),
            actions = {
                TextButton (
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.Cyan)
                ) {
                    Text("Saved Quotes", fontSize = 18.sp)
                }

            }
        )
    }
}

