package com.nemesisprotocol.cryptocraze.domain.paymentinfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.CryptoCrazeVisaColour

@Entity(tableName = "crypto_craze_visa_card_info")
data class CryptoCrazeVisaCard(
    @PrimaryKey(autoGenerate = true) val cardId: Int = 0,
    @ColumnInfo val balance: Double = 1000000.00,
    @ColumnInfo val cryptoCrazeVisaColour: CryptoCrazeVisaColour
)
