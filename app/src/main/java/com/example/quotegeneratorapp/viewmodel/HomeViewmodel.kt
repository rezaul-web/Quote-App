package com.example.quotegeneratorapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotegeneratorapp.model.Quote
import com.example.quotegeneratorapp.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<Quote>>(UIState.Loading)
    val uiState: StateFlow<UIState<Quote>> = _uiState

    private val _buttonState = MutableStateFlow(false)
    val buttonState: StateFlow<Boolean> = _buttonState

    fun updateState() {
        _buttonState.value = true // Start loading indicator
        fetchQuote() // Fetch quote without immediately updating the UIState
    }

    init {
        fetchQuote() // Initial load
    }

    private fun fetchQuote() {
        viewModelScope.launch {
            try {
                quoteRepository.collectRandomQuote()
                    .catch { e ->
                        _uiState.value = UIState.Error(e.message.toString())
                    }
                    .collect { quote ->
                        _uiState.value = UIState.Success(quote) // Update UIState after successful fetch
                    }
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message.toString())
            } finally {
                _buttonState.value = false // Stop loading indicator
            }
        }
    }
}




sealed class UIState<out T> {
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
}