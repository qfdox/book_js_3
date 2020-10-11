
package com.nemesisprotocol.cryptocraze.data.database.paymentinfo

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nemesisprotocol.cryptocraze.data.database.converters.CryptoCrazeVisaColourConverters
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.FiatWalletCard

@Database(
    entities = [FiatWalletCard::class, CryptoCrazeVisaCard::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CryptoCrazeVisaColourConverters::class)
abstract class PaymentInfoDatabase : RoomDatabase() {
    abstract fun paymentInfoDao(): PaymentInfoDao
}