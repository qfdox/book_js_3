package com.nemesisprotocol.cryptocraze.presentation.infoscreen.coininfolist

import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.Coin

data class CoinInfoListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
