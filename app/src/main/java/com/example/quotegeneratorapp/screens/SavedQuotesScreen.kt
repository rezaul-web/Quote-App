package com.example.quotegeneratorapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    databaseViewModel: DatabaseViewModel = hiltViewModel(),
    showTopBar: Boolean = false
) {
    val allQuotes by databaseViewModel.allQuotes.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(onClick = {
                        showDeleteDialog = true
                    }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete All Quotes",
                            Modifier.size(50.dp),
                            tint = Color.Red
                        )
                    }
                },
                title = { Text("Saved Quotes") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Cyan
                ),

            )
            if (showDeleteDialog) {
                DeleteAlertDialog(
                    onConfirmation = {
                        databaseViewModel.deleteAllQuote()
                        showDeleteDialog = false
                    },
                    onDismissRequest = { showDeleteDialog = false }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues) // Apply the scaffold's padding to prevent content overlap
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn {
                items(allQuotes) { quote ->
                    SaveQuoteCard(quoteItem = quote, databaseViewModel = databaseViewModel)
                }
            }
        }
    }
}

