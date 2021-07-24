package com.nemesisprotocol.cryptocraze.presentation.infoscreen.coininfo

import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.CoinDetail

data class CoinInfoState(
    val isLoading: Boolean = false,
    val coin: CoinDetail?