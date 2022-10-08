package com.example.jltodoapp.domain.get_calls

import com.example.jltodoapp.data.Result
import com.example.jltodoapp.data.model.CallItem
import com.example.jltodoapp.data.repository.CallRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCallsUseCase @Inject constructor(
    private val repository: CallRepository
) {
    operator fun invoke(): Flow<Result<List<CallItem>>> {
        return repository.getCalls()
    }
}