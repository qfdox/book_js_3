
package com.nemesisprotocol.cryptocraze.domain.cryptodata.usecase

import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoFavDataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckFavCryptoExistsUseCase @Inject constructor(private val cryptoFavDataRepo: CryptoFavDataRepo) {
    suspend operator fun invoke(cryptoName: String) =
        withContext(Dispatchers.Default) {
            cryptoFavDataRepo.checkFavCryptoExists(cryptoName)
        }
}