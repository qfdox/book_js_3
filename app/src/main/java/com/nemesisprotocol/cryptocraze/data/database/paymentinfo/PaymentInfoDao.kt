
package com.nemesisprotocol.cryptocraze.data.database.paymentinfo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.FiatWalletCard

@Dao
@TypeConverters()
interface PaymentInfoDao {

    @Query("SELECT * FROM fiat_wallet_card_info")
    fun getFiatWallets(): List<FiatWalletCard>

    @Query("SELECT * FROM fiat_wallet_card_info WHERE cardNumber =:cardNumber")
    fun getFiatWalletByCardNumber(cardNumber: Long): FiatWalletCard

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFiatWallet(fiatWalletCard: FiatWalletCard)

    @Update
    fun updateFiatWallet(fiatWalletCard: FiatWalletCard)

    @Delete
    fun deleteFiatWallet(fiatWalletCard: FiatWalletCard)

    @Query("SELECT * from crypto_craze_visa_card_info")
    fun getCryptoCrazeVisaCards(): List<CryptoCrazeVisaCard>

    @Query("SELECT * FROM crypto_craze_visa_card_info WHERE cardId =:cardId")
    fun getCryptoCrazeVisaCardByCardId(cardId: Int): CryptoCrazeVisaCard

    @Insert
    fun addCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard)

    @Update
    fun updateCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard)

    @Delete
    fun deleteCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard)
}