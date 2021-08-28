package com.nemesisprotocol.cryptocraze.presentation.infoscreen.coininfolist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemesisprotocol.cryptocraze.common.Resource
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinInfoListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinInfoListState())
    val state: State<CoinInfoListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinInfoListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinInfoListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinInfoListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
