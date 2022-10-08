package com.example.jltodoapp.presentation.calllist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jltodoapp.R
import com.example.jltodoapp.data.model.CallItem
import com.example.jltodoapp.domain.get_calls.GetCallsUseCase
import com.example.jltodoapp.util.Async
import com.example.jltodoapp.data.Result
import com.example.jltodoapp.data.Result.Success
import com.example.jltodoapp.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * UiState for the Call screen
 */
data class CallUiState(
    val calls: List<CallItem>? = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)

@HiltViewModel
class CallListViewModel @Inject constructor(
    private val getCallsUseCase: GetCallsUseCase
) : ViewModel() {

    private val _userMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _isLoading = MutableStateFlow(false)

    private val _callAsync = getCallsUseCase.invoke()
        .map { handleResult(it) }
        .onStart { emit(Async.Loading) }

    val uiState: StateFlow<CallUiState> = combine(
        _isLoading, _userMessage, _callAsync
    ) { isLoading, userMessage, callAsync ->
        when (callAsync) {
            Async.Loading -> {
                CallUiState(isLoading = true)
            }
            is Async.Success -> {
                CallUiState(
                    calls = callAsync.data,
                    isLoading = isLoading,
                    userMessage = userMessage
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = CallUiState(isLoading = true)
    )

    fun snackbarMessageShown() {
        _userMessage.value = null
    }

    private fun showSnackbarMessage(message: Int) {
        _userMessage.value = message
    }

    private fun handleResult(callsResult: Result<List<CallItem>>): Async<List<CallItem>?> =
        if (callsResult is Success) {
            Async.Success(callsResult.data)
        } else {
            //TODO catch error message here
            showSnackbarMessage(R.string.something_went_wrong)
            Async.Success(null)
        }

}