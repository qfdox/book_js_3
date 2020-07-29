package com.nemesisprotocol.cryptocraze.di

import android.content.Context
import androidx.room.Room
import com.nemesisprotocol.cryptocraze.data.database.cryptofavdata.CryptoFavDataDatabase
import com.nemesisprotocol.cryptocraze.data.database.paymentinfo.PaymentInfoDatabase
import com.nemesisprotocol.cryptocraze.data.database.portfolio.PortfolioDatabase
import com.nemesisprotocol.cryptocraze.data.database.transactionhistory.TransactionHistoryDatabase
import com.nemesisprotocol.cryptocraze.data.database.user.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_user_db")
    fun provideTestUserDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, UserDatabase::class.java
        ).allowMainThreadQueries()
            .build()

    @Provides
    @Named("test_crypto_fav_data_db")
    fun provideTestCryptoDataDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, CryptoFavDataDatabase::class.java
        ).allowMainThreadQueries()
            .build()

    @Provides
    @Named("test_payment_info_db")
    fun provideTestPaymentInfoDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, PaymentInfoDatabase::class.java
        ).allowMainThreadQueries()
            .build()

    @Provides
    @Named("test_transaction_history_db")
    fun provideTestTransactionHistoryDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, TransactionHistoryDatabase::class.java
        ).allowMainThreadQueries()
            .build()

    @Provides
    @N