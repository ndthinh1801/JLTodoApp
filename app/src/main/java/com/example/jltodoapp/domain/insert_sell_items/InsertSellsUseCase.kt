package com.example.jltodoapp.domain.insert_sell_items

import com.example.jltodoapp.data.model.ListingItem
import com.example.jltodoapp.data.repository.ListingRepository
import javax.inject.Inject

class InsertSellsUseCase @Inject constructor(
    private val repository: ListingRepository
) {
    suspend fun invoke(items: List<ListingItem>) {
        return repository.insertSells(items)
    }
}