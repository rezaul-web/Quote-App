package com.example.quotegeneratorapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotegeneratorapp.model.QuoteItem
import com.example.quotegeneratorapp.viewmodel.DatabaseViewModel

@Composable
fun SaveQuoteCard(
    modifier: Modifier = Modifier, quoteItem: QuoteItem,
    databaseViewModel: DatabaseViewModel = hiltViewModel(),
) {
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    Card(
        modifier = Modifier.padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEDE8D0),
            contentColor = Color.Black
        )
    ) {

        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = quoteItem.q, fontSize = 24.sp, fontFamily = FontFamily.SansSerif)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = quoteItem.a, fontSize = 14.sp, fontFamily = FontFamily.SansSerif)
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {
                    showDialog.value = true
                }) {
                    Icon(
                        Icons.Default.Delete, contentDescription = null, tint = Color.Red,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
    if (showDialog.value) {
        DeleteAlertDialog(
            onDismissRequest = { showDialog.value = false },
            onConfirmation = {
                databaseViewModel.deleteQuote(quoteItem)
                Toast.makeText(context, "Quote Deleted", Toast.LENGTH_SHORT).show()
                showDialog.value = false
            }
        )
    }
}

