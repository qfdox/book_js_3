package com.nemesisprotocol.cryptocraze.domain.cryptodata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "crypto_favorites")
data class CryptoData(
    @PrimaryK