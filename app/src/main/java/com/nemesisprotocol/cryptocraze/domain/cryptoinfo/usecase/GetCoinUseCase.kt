
package com.nemesisprotocol.cryptocraze.domain.cryptoinfo.usecase

import com.nemesisprotocol.cryptocraze.common.Resource
import com.nemesisprotocol.cryptocraze.data.cryptoinfo.remote.dto.toCoinDetail
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.CoinDetail
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.CoinRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepo
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An error has occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Error has occurred check your internet connection."))
        }
    }
}