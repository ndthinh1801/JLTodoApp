package com.example.jltodoapp.data.remote

import com.example.jltodoapp.data.Result
import com.example.jltodoapp.data.dto.toCallItem
import com.example.jltodoapp.data.dto.toItem
import com.example.jltodoapp.data.model.CallItem
import com.example.jltodoapp.data.model.ListingItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkService @Inject constructor(
    private val apiService: ApiService
) {
    fun getCalls(): Flow<Result<List<CallItem>>> = flow {
        try {
            val result = apiService.getCalls().map {
                //map DTO to item which will be showed to UI
                it.toCallItem()
            }
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    fun getBuys(): Flow<Result<List<ListingItem>>> = flow {
        try {
            val result = apiService.getBuyList().map {
                //map DTO to item which will be showed to UI
                it.toItem()
            }
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}