package com.example.quotegeneratorapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_table")
data class QuoteItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    val a: String,
    val h: String,
    val q: String
)