package com.example.quotegeneratorapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quotegeneratorapp.model.QuoteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quoteItem: QuoteItem)

    @Delete
    suspend fun delete(quoteItem: QuoteItem)

    @Query("delete from quote_table")
    suspend fun deleteAll()

    @Query("select * from quote_table")
    fun getAllQuotes(): Flow<List<QuoteItem>>


}