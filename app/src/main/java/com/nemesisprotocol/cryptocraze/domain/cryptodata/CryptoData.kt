package com.nemesisprotocol.cryptocraze.domain.cryptodata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "crypto_favorites")
data class CryptoData(
    @PrimaryKey
    val symbol: String,
    @ColumnInfo val price: Double,
    @ColumnInfo val name: String,
    @ColumnInfo val image: String,
    @ColumnInfo val dailyChange: Double,
    @ColumnInfo val dai