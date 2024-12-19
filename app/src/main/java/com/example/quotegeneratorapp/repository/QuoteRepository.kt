package com.example.quotegeneratorapp.repository

import com.example.quotegeneratorapp.model.Quote
import com.example.quotegeneratorapp.network.QuoteApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteApi: QuoteApi

)
{
    suspend fun collectRandomQuote():Flow<Quote> {
        return flow {
            emit(quoteApi.getRandomQuote())
        }.catch {

        }
    }

    }


