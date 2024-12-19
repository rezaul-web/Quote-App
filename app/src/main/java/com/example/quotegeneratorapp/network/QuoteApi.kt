package com.example.quotegeneratorapp.network

import com.example.quotegeneratorapp.model.Quote
import retrofit2.http.GET

interface QuoteApi {
    @GET("random")
    suspend fun getRandomQuote():Quote
}