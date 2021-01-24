
package com.nemesisprotocol.cryptocraze.domain.cryptoinfo.usecase

import com.nemesisprotocol.cryptocraze.common.Resource
import com.nemesisprotocol.cryptocraze.data.cryptoinfo.remote.dto.toCoin
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.Coin
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.CoinRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinRepo: CoinRepo) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = coinRepo.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An error has occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Error occurred check your internet connection."))
        }
    }
}