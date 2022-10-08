package com.example.jltodoapp.presentation.listing

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jltodoapp.R
import com.example.jltodoapp.common.Constants
import com.example.jltodoapp.data.Result
import com.example.jltodoapp.data.Result.Success
import com.example.jltodoapp.data.model.ListingItem
import com.example.jltodoapp.domain.get_buy_items.GetBuysUseCase
import com.example.jltodoapp.domain.get_sell_items.GetSellsUseCase
import com.example.jltodoapp.domain.insert_sell_items.InsertSellsUseCase
import com.example.jltodoapp.util.Async
import com.example.jltodoapp.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

/**
 * UiState for the Listing screen
 */
data class ListingUiState(
    val items: List<ListingItem>? = null,
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val insertSellsUseCase: InsertSellsUseCase,
    private val getBuysUseCase: GetBuysUseCase,
    private val getSellsUseCase: GetSellsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val listingType = savedStateHandle.get<Serializable>(Constants.NAV_PARAM_LISTING_TYPE)
    private val _userMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _isLoading = MutableStateFlow(false)

    init {
        //Create fake data to test Room data base
        if (listingType == ListingType.SELL.name) {
            val item1 = ListingItem(1, "iPhone X", 150000.0, 1, 2)
            val item2 = ListingItem(2, "TV", 38000.0, 2, 2)
            val item3 = ListingItem(3, "Table", 12000.0, 1, 2)
            viewModelScope.launch {
                insertSellsUseCase.invoke(listOf(item1, item2, item3))
            }
        }
    }

    private val _buysAsync =
        if (listingType == ListingType.BUY.name) {
            getBuysUseCase.invoke()
        } else {
            getSellsUseCase.invoke()
        }
            .map { handleResult(it) }
            .onStart { emit(Async.Loading) }

    val uiState: StateFlow<ListingUiState> = combine(
        _isLoading, _userMessage, _buysAsync
    ) { isLoading, userMessage, buysAsync ->
        when (buysAsync) {
            Async.Loading -> {
                ListingUiState(isLoading = true)
            }
            is Async.Success -> {
                ListingUiState(
                    items = buysAsync.data,
                    isLoading = isLoading,
                    userMessage = userMessage
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = ListingUiState(isLoading = true)
    )

    fun snackbarMessageShown() {
        _userMessage.value = null
    }

    private fun showSnackbarMessage(message: Int) {
        _userMessage.value = message
    }

    private fun handleResult(callsResult: Result<List<ListingItem>>): Async<List<ListingItem>?> =
        if (callsResult is Success) {
            Async.Success(callsResult.data)
        } else {
            //TODO catch error message here
            showSnackbarMessage(R.string.something_went_wrong)
            Async.Success(null)
        }

}