
package com.nemesisprotocol.cryptocraze.data.di

import com.nemesisprotocol.cryptocraze.common.Constants
import com.nemesisprotocol.cryptocraze.common.DispatcherProvider
import com.nemesisprotocol.cryptocraze.data.cryptodata.remote.CryptoDataApi
import com.nemesisprotocol.cryptocraze.data.cryptodata.remote.CryptoDataApiMapper
import com.nemesisprotocol.cryptocraze.data.cryptoinfo.remote.dto.CoinPaprikaApi
import com.nemesisprotocol.cryptocraze.data.repos.CoinRepoImpl
import com.nemesisprotocol.cryptocraze.data.repos.CryptoDataRepoImpl
import com.nemesisprotocol.cryptocraze.data.repos.CryptoFavDataRepoImpl
import com.nemesisprotocol.cryptocraze.data.repos.PaymentInfoRepoImpl
import com.nemesisprotocol.cryptocraze.data.repos.PortfolioRepoImpl
import com.nemesisprotocol.cryptocraze.data.repos.TransactionHistoryRepoImpl
import com.nemesisprotocol.cryptocraze.data.repos.UserRepoImpl
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoDataRepo
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoFavDataRepo
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.CoinRepo
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.PaymentInfoRepo
import com.nemesisprotocol.cryptocraze.domain.portfolio.PortfolioRepo
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionHistoryRepo
import com.nemesisprotocol.cryptocraze.domain.user.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepo(userRepoImpl: UserRepoImpl): UserRepo = userRepoImpl

    @Provides
    @Singleton
    fun provideCryptoDataApi(): CryptoDataApi {
        return Retrofit.Builder()