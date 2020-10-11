
package com.nemesisprotocol.cryptocraze.data.database.transactionhistory

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionRecord

@Dao
@TypeConverters()
interface TransactionHistoryDao {

    @Query("SELECT * FROM transaction_records")
    fun getTransactionRecords(): List<TransactionRecord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTransactionRecord(transactionRecord: TransactionRecord)

    @Delete
    fun deleteTransactionRecord(transactionRecord: TransactionRecord)
}