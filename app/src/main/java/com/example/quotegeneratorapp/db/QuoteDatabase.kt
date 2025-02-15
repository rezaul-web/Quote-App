package com.example.quotegeneratorapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quotegeneratorapp.model.QuoteItem

@Database(entities = [QuoteItem::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

   abstract fun getQuoteDao(): QuoteDao


}
