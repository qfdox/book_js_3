
package com.nemesisprotocol.cryptocraze.domain.cryptodata

interface CryptoDataRepo {
    suspend fun getPageCryptos(page: Int, pageSize: Int): List<CryptoData>
    suspend fun getCryptoDataBySymbol(symbol: String): List<CryptoData>
}