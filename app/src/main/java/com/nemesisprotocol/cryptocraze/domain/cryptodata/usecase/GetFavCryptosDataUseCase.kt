package com.nemesisprotocol.cryptocraze.domain.cryptodata.usecase

import androidx.lifecycle.LiveData
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoData
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoFavDataRepo
import javax.inject.Inject

class GetFavCryptosDataUseCase @Inject constructor(private val cryptoFavDataRepo: CryptoFavDataRepo) {
    suspend operator fun invoke(): LiveData<List<CryptoData>> = cryptoFavDataRepo.getFavCryptos()
}
