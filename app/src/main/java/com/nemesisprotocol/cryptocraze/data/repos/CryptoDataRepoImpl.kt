package com.nemesisprotocol.cryptocraze.data.repos

import androidx.annotation.WorkerThread
import com.nemesisprotocol.cryptocraze.data.cryptodata.remote.CryptoDataApi
import com.nemesisprotocol.cryptocraze.data.cryptodata.remote.CryptoDataApiMapper
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoData
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoDataRepo
import javax.inject.Inject

class CryptoDataRepoImpl @Inject constructor(
    private val cryptoApi: CryptoDataApi,
    private val cryptoApiMapper: CryptoDataApiMapper
) : CryptoDataRepo {

    @WorkerThread
    override suspend fun getPageCryptos(page: Int, pageSize: Int): List<CryptoData> {
        v