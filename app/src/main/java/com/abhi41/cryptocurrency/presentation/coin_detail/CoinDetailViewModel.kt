package com.abhi41.cryptocurrency.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi41.cryptocurrency.common.CoinDetailNavArgs
import com.abhi41.cryptocurrency.common.Constants
import com.abhi41.cryptocurrency.common.Resource
import com.abhi41.cryptocurrency.domain.use_case.get_single_coin.GetSingleCoinUseCase
import com.abhi41.cryptocurrency.presentation.destinations.CoinDetailScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getSingleCoinUseCase: GetSingleCoinUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        val navArgs: CoinDetailNavArgs = CoinDetailScreenDestination.argsFrom(savedStateHandle)
        getCoinById(navArgs.id)
    }

    private fun getCoinById(coinId: String) {
        getSingleCoinUseCase(coinId = coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value =
                        CoinDetailState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}