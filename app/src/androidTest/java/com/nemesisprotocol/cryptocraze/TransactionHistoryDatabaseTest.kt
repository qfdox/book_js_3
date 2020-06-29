package com.nemesisprotocol.cryptocraze

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.nemesisprotocol.cryptocraze.data.database.transactionhistory.TransactionHistoryDao
import com.nemesisprotocol.cryptocraze.data.database.transactionhistory.TransactionHistoryDatabase
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionRecord
import com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen.TransactionType
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class TransactionHistoryDatabaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_transaction_history_db")
    lateinit var database: TransactionHistoryDatabase
    private lateinit var transactionHistoryDao: TransactionHistoryDao

    @Before
    fun setup() {
        hiltRule.inject()
        transactionHistoryDao = database.transactionHistoryDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_addTransactionRecord() = runBlockingTest {
        val uuid = UUID.randomUUID().toString()
        val transactionRecord =
            TransactionRecord(uuid, "BTC", 2.0, "5", Date(), TransactionType.BUY)
        transactionHistoryDao.addTransactionRecord(transactionRecord)
        val transactionHistory = transactionHistoryDao.getTransactionRecords()
        Truth.assertThat(transactionHistory).contains(transactionRecord)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_deleteTransactionRecord() = runBlockingTest {
        val uuid = UUID.randomUUID().toString()
        val transactionRecord =
            TransactionRecord(uuid, "BTC", 2.0, "5", Date(), TransactionType.BUY)
        transactionHistoryDao.addTransactionRecord(trans