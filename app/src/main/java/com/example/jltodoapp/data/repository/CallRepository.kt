package com.example.jltodoapp.data.repository

import com.example.jltodoapp.data.Result
import com.example.jltodoapp.data.model.CallItem
import com.example.jltodoapp.data.remote.NetworkService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CallRepository @Inject constructor(
    private val networkService: NetworkService
) {
    fun getCalls(): Flow<Result<List<CallItem>>> {
        return networkService.getCalls()
    }
}