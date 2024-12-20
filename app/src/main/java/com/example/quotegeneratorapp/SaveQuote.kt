package com.example.quotegeneratorapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotegeneratorapp.viewmodel.DatabaseViewModel

@Composable
fun SaveQuote(modifier: Modifier = Modifier,
              databaseViewModel: DatabaseViewModel= hiltViewModel()
              ) {


    val quotes=databaseViewModel.allQuotes.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = quotes.value.toString())
    }

}