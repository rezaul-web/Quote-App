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
): ViewModel() {
    private val _allQuotes = MutableStateFlow<List<QuoteItem>>(emptyList())
    val allQuotes: StateFlow<List<QuoteItem>> = _allQuotes


    // Initialize and start collecting quotes when the ViewModel is created
     fun insertQuote(quoteItem: QuoteItem) {
        viewModelScope.launch {
            quoteDao.insert(quoteItem)
        }


    }
    suspend fun getAllQuotes()= withContext(Dispatchers.IO){
        quoteDao.getAllQuotes()
    }
}
