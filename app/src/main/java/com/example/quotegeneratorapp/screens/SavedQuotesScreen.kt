package com.example.quotegeneratorapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotegeneratorapp.viewmodel.DatabaseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedQuotesScreen(
    modifier: Modifier = Modifier,
    databaseViewModel: DatabaseViewModel = hiltViewModel()
) {
    val allQuotes by databaseViewModel.allQuotes.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Saved Quotes") }
            )
        }
    ) { paddingValues -> // This paddingValues will automatically handle the padding
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues) // Apply the scaffold's padding to prevent content overlap
                , // Optional additional padding for content
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn {
                items(allQuotes) { quote ->
                    QuoteView(
                        quote = quote,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Gray)
                    )
                }
            }
        }
    }
}

