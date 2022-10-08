package com.example.jltodoapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jltodoapp.data.model.ListingItem
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Item to Sell class.
 */
@Dao
interface ItemToSellDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSellItems(items: List<ListingItem>)

    @Query("select * from ItemToSell ORDER by id")
    fun getItemToSell(): Flow<List<ListingItem>>
}