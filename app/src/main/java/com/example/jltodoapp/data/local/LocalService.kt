package com.example.jltodoapp.data.local

import com.example.jltodoapp.data.Result
import com.example.jltodoapp.data.model.ListingItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalService @Inject constructor(
    private val itemToSellDao: ItemToSellDao
) {
    suspend fun insertSells(items: List<ListingItem>) {
        itemToSellDao.insertSellItems(items)
    }

    fun getSells(): Flow<Result<List<ListingItem>>> {
        return itemToSellDao.getItemToSell().map {
            Result.Success(it)
        }
    }
}