package com.nemesisprotocol.cryptocraze.data.repos

import com.nemesisprotocol.cryptocraze.data.database.transactionhistory.TransactionHistoryDao
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionHistoryRepo
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionRecord
import javax.inject.Inject

class TransactionHistoryRepoImpl @Inject constructor(private val transactionHistoryDao: TransactionHistoryDao) :
    TransactionHistoryRepo {

    override fun get