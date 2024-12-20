package com.example.quotegeneratorapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quotegeneratorapp.model.QuoteItem

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