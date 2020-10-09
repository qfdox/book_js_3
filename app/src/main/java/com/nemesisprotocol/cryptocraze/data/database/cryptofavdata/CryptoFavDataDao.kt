package com.nemesisprotocol.cryptocraze.data.database.cryptofavdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoData

@Dao
@TypeConverters()
interface CryptoFavDataDao {

    @Transaction
    @Query("SELECT * FROM crypto_favorites")
    fun getFavCryptos(): LiveData<List<CryptoData>>

    @Insert
    fun addFav(favCrypto: CryptoData)

    