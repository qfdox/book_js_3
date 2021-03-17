package com.nemesisprotocol.cryptocraze.domain.transactionhistory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen.TransactionType
import java.util.Date
import java.util.UUID

@Entity(tableName = "transaction_records")
data class TransactionRecord(
    @PrimaryKey val transactionUuid: String = UUID.randomUUID().toString(),
    @ColumnInfo val cryptoSymbol