package com.example.jltodoapp.data.repository

import com.example.jltodoapp.data.Result
import com.example.jltodoapp.data.local.LocalService
import com.example.jltodoapp.data.model.ListingItem
import com.example.jltodoapp.data.remote.NetworkService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListingRepository @Inject constructor(
    private val networkService: NetworkService,
    private val localService: LocalService
) {
    fun getBuys(): Flow<Result<List<ListingItem>>> {
        return networkService.getBuys()
    }

    fun getSells(): Flow<Result<List<ListingItem>>> {
        return localService.getSells()
    }

    suspend fun insertSells(items: List<ListingItem>) {
        localService.insertSells(items)
    }
}