
package com.nemesisprotocol.cryptocraze.domain.cryptodata

import androidx.lifecycle.LiveData

interface CryptoFavDataRepo {
    suspend fun getFavCryptos(): LiveData<List<CryptoData>>
    suspend fun addFav(favCryptoData: CryptoData)
    suspend fun removeFav(cryptoData: CryptoData)
    suspend fun checkFavCryptoExists(name: String): Boolean
    suspend fun wipeFavorites()
}