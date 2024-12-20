package com.example.quotegeneratorapp.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotegeneratorapp.db.QuoteDao
import com.example.quotegeneratorapp.model.QuoteItem
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val quoteDao: QuoteDao
) : ViewModel() {

    private val _allQuotes = MutableStateFlow<List<QuoteItem>>(emptyList())
    val allQuotes: StateFlow<List<QuoteItem>> = _allQuotes
init {
    getAllQuotes()
}
    // Insert a quote item into the database
    fun insertQuote(quoteItem: QuoteItem) {
        viewModelScope.launch {
            // Ensure insert happens on a background thread
            quoteDao.insert(quoteItem)
        }
    }

    // Get all quotes from the database and collect them
    private fun getAllQuotes() {
        viewModelScope.launch {
            quoteDao.getAllQuotes().collect { quotes ->
                _allQuotes.value = quotes
            }
        }
    }

    fun deleteQuote(quoteItem: QuoteItem) {
        viewModelScope.launch {
            quoteDao.delete(quoteItem)
        }
    }
    fun deleteAllQuote() {
        viewModelScope.launch {
            quoteDao.deleteAll()
        }
    }
}

