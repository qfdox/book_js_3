
package com.nemesisprotocol.cryptocraze.data.database.cryptofavdata

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nemesisprotocol.cryptocraze.data.database.converters.ListTypeConverters
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoData

@Database(entities = [CryptoData::class], version = 1, exportSchema = false)
@TypeConverters(ListTypeConverters::class)
abstract class CryptoFavDataDatabase : RoomDatabase() {
    abstract fun cryptoFavDataDao(): CryptoFavDataDao
}