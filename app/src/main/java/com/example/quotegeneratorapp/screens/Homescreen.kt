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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotegeneratorapp.model.QuoteItem
import com.example.quotegeneratorapp.viewmodel.HomeViewmodel
import com.example.quotegeneratorapp.viewmodel.UIState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewmodel = hiltViewModel()
) {
    val randomQuoteState = viewModel.uiState.collectAsState()
    val buttonState = viewModel.buttonState.collectAsState()

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
                    onClick = { viewModel.updateState() },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "Generate")
                }
            }


        }


    }
    }



@Composable
fun QuoteView(
    modifier: Modifier = Modifier, quote: QuoteItem
) {
    Column {
        Text(
            text = quote.q,
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(8.dp),
            color = Color.Black
        )
        Text(
            text = quote.a,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End),
            color = Color.Black
        )


    }
}


