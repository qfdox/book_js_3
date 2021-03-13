package com.nemesisprotocol.cryptocraze.domain.transactionhistory

interface TransactionHistoryRepo {
    fun getTransactionRecords(): List<TransactionRecord>
    fun addTransactionRecord(transactionRecord: TransactionRecord)
 