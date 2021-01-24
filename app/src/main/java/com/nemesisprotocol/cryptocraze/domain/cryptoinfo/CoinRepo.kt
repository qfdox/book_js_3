package com.nemesisprotocol.cryptocraze.domain.cryptoinfo

import com.nemesisprotocol.cryptocraze.data.cryptoinfo.remote.dto.CoinDto
import com.nemesisprotocol.cryptocraze.data.cryptoinfo.remote.dto.CoinInfoDto

interface CoinRepo {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinInfoDto
}
