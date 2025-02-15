package com.example.quotegeneratorapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotegeneratorapp.viewmodel.DatabaseViewModel
import com.example.quotegeneratorapp.viewmodel.HomeViewmodel
import com.example.quotegeneratorapp.viewmodel.UIState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewmodel: HomeViewmodel = hiltViewModel(),
    databaseViewModel: DatabaseViewModel = hiltViewModel()
) {
    val randomQuoteState = homeViewmodel.uiState.collectAsState()
    val buttonState = homeViewmodel.buttonState.collectAsState()
    val isSavedState = homeViewmodel.isSaved.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (val state = randomQuoteState.value) {
            is UIState.Loading -> {
                CircularProgressIndicator()
            }

            is UIState.Success -> {


                QuoteView(
                    quote = state.data[0]
                )
            }

            is UIState.Error -> {
                Text(text = "Error: ${state.message}")
            }
        }
        Spacer(modifier = Modifier.size(34.dp))
        Box(
            modifier = Modifier.padding(top = 16.dp),

            ) {
            if (buttonState.value) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            } else {
                Button(
                    onClick = {
                        homeViewmodel.updateState()
                        homeViewmodel.updateSaveQuoteButtonState(true)
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "Generate", fontSize = 18.sp)
                }
            }


        }
        Spacer(modifier = Modifier.size(34.dp))
        Button(
            onClick = {
                val state = randomQuoteState.value
                if (state is UIState.Success) {
                    databaseViewModel.insertQuote(state.data[0])
                    homeViewmodel.updateSaveQuoteButtonState(false)
                }
            },
            enabled = isSavedState.value,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
        ) {
            if (!isSavedState.value) {
                Text(text = "Saved", fontSize = 18.sp)
            } else {
                Text(text = "Save", fontSize = 18.sp)
            }
        }


    }
}


