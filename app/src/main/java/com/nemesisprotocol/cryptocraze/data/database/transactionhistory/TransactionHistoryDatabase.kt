
package com.nemesisprotocol.cryptocraze.data.database.transactionhistory

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nemesisprotocol.cryptocraze.data.database.converters.TimestampConverters
import com.nemesisprotocol.cryptocraze.data.database.converters.TransactionTypeConverters
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionRecord

@Database(entities = [TransactionRecord::class], version = 1, exportSchema = false)
@TypeConverters(TimestampConverters::class, TransactionTypeConverters::class)
abstract class TransactionHistoryDatabase : RoomDatabase() {
    abstract fun transactionHistoryDao(): TransactionHistoryDao
}