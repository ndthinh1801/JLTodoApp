package com.example.jltodoapp.domain.get_sell_items

import com.example.jltodoapp.data.Result
import com.example.jltodoapp.data.model.ListingItem
import com.example.jltodoapp.data.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSellsUseCase @Inject constructor(
    private val repository: ListingRepository
) {
    operator fun invoke(): Flow<Result<List<ListingItem>>> {
        return repository.getSells()
    }
}