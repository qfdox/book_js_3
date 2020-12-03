package com.nemesisprotocol.cryptocraze.data.repos

import androidx.lifecycle.LiveData
import com.nemesisprotocol.cryptocraze.data.database.cryptofavdata.CryptoFavDataDao
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoData
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoFavDataRepo
import javax.inject.Inject

class CryptoFavDataRepoImpl @Inject constructor(private val cryptoFavDataDao: CryptoFavDataDao) :
    CryptoFavDataRepo {

    override suspend fun getFavCryptos(): LiveData<List<CryptoData>> {
        return cryptoFavDataDao.getFavCryptos()
    }

