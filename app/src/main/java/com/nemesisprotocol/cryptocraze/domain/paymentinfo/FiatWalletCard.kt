package com.nemesisprotocol.cryptocraze.domain.paymentinfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fiat_wallet_card_info")
data class FiatWalletCard(
    @Primar