package com.nemesisprotocol.cryptocraze.data.repos

import com.nemesisprotocol.cryptocraze.data.database.transactionhistory.TransactionHistoryDao
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionHistoryRepo
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionRecord
import javax.inject.Inject

class TransactionHistoryRepoImpl @Inject constructor(private val transactionHistoryDao: TransactionHistoryDao) :
    TransactionHistoryRepo {

    override fun getTransactionRecords(): List<TransactionRecord> {
        return transactionHistoryDao.getTransactionRecords()
    }

    override fun addTransactionRecord(transactionRecord: TransactionRecord) {
        transactionHistoryDao.addTransactionRecord(transactionRecord = transactionRecord)
    }

    override fun deleteTransactionRecord(transactionRecord: TransactionRecord) {
        transactionHis