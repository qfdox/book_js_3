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
    @ColumnInfo val dailyChangePercentage: Double,
    @ColumnInfo val high: Double,
    @ColumnInfo val low: Double,
    @ColumnInfo val marketCap: Long,
    @ColumnInfo val volume: Double,
    @ColumnInfo val supply: Double?,
    @ColumnInfo val chartData: List<Float>
) : Serializable {

    fun mapPriceInfo(): CryptoDataPriceInfo {
        return CryptoDataPriceInfo(symbol, price, name)
    }

    override fun equals(other: Any?): Boolean {
        return (other as CryptoData).symbol == symbol
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + dailyChange.hashCode()
 