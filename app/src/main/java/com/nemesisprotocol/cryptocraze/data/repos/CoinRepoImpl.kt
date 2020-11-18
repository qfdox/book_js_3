package com.nemesisprotocol.cryptocraze.data.repos

import com.nemesisprotocol.cryptocraze.data.cryptoinfo.remote.dto.CoinDto
import com.nemesisprotocol.cryptocraze.data.cryptoinfo.remote.dto.CoinInfoDto
import com.nemesisprotocol.cryptocraze.data.cryptoinfo.remote.dto.CoinPaprikaApi
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.CoinRepo
import javax.inject.Inject

class CoinRepoImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepo {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend f