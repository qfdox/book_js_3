package com.nemesisprotocol.cryptocraze.data.database.converters

import androidx.room.TypeConverter
import com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen.TransactionType

class TransactionTypeConverters {

    @TypeConverter
    fun toTransactionType(value: String) = enumValueOf<TransactionType>(value)

    @TypeConverter
    fun fromTransactionType(transactionType: TransactionType) =
        transactionType.name
}
