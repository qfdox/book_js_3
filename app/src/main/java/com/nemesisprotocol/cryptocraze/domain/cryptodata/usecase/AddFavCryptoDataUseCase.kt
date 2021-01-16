package com.nemesisprotocol.cryptocraze.domain.cryptodata.usecase

import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoData
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoFavDataRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddFavCryptoDataUseCase @Inject constructor(private val cryptoFavDataRepo: CryptoFavDataRepo) {
    private val addFavCryptoDataCoroutineScope = CoroutineScope(Dispatchers.Default)
    operator fun invoke(cryptoData: CryptoData) =
        addFavCryptoDataCoroutineScope.launch {
            cryptoFavDataRepo.addFav(cryptoData)
     