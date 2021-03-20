package com.nemesisprotocol.cryptocraze.domain.transactionhistory.usecase

import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionHistoryRepo
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionRecord
import javax.inject.Inject

class DeleteTransactionRecordUseCase @Inject constructor(private val transactionHistoryRepo: TransactionHistoryRepo) {
    operator fun invoke(transactionRecord: TransactionRecord) =
        transactionHistoryRepo.deleteTransactionRecord(transactionRecord)
}
