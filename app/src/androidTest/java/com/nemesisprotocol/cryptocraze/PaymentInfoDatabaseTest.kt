
package com.nemesisprotocol.cryptocraze

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.nemesisprotocol.cryptocraze.data.database.paymentinfo.PaymentInfoDao
import com.nemesisprotocol.cryptocraze.data.database.paymentinfo.PaymentInfoDatabase
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.FiatWalletCard
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.CryptoCrazeVisaColour
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class PaymentInfoDatabaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_payment_info_db")
    lateinit var database: PaymentInfoDatabase
    private lateinit var paymentInfoDao: PaymentInfoDao

    @Before
    fun setup() {
        hiltRule.inject()
        paymentInfoDao = database.paymentInfoDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_addFiatWallet() = runBlockingTest {
        val fiatWalletCard = FiatWalletCard(2415634636346, "User1", 5151, 123, 53620.00)
        paymentInfoDao.addFiatWallet(fiatWalletCard)
        val fiatWallets = paymentInfoDao.getFiatWallets()
        Truth.assertThat(fiatWallets).contains(fiatWalletCard)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_updateFiatWallet() = runBlockingTest {
        var fiatWalletCard = FiatWalletCard(2415634636346, "User1", 5151, 123, 53620.00)
        paymentInfoDao.addFiatWallet(fiatWalletCard)
        var fiatWallets = paymentInfoDao.getFiatWallets()
        Truth.assertThat(fiatWallets).contains(fiatWalletCard)
        Truth.assertThat(fiatWallets[0].balance).isEqualTo(53620.00)
        fiatWalletCard = FiatWalletCard(2415634636346, "User1", 5151, 123, 12134.00)
        paymentInfoDao.updateFiatWallet(fiatWalletCard)
        fiatWallets = paymentInfoDao.getFiatWallets()
        Truth.assertThat(fiatWallets[0].balance).isEqualTo(12134.00)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_deleteFiatWallet() = runBlockingTest {
        val fiatWalletCard = FiatWalletCard(2415634636346, "User1", 5151, 123, 53620.00)
        paymentInfoDao.addFiatWallet(fiatWalletCard)
        var fiatWallets = paymentInfoDao.getFiatWallets()
        Truth.assertThat(fiatWallets.size).isEqualTo(1)
        paymentInfoDao.deleteFiatWallet(fiatWalletCard)
        fiatWallets = paymentInfoDao.getFiatWallets()
        Truth.assertThat(fiatWallets.size).isEqualTo(0)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_getFiatWalletByCardNumber() = runBlockingTest {
        val fiatWalletCard1 = FiatWalletCard(2415634636346, "User1", 5151, 123, 53620.00)
        val fiatWalletCard2 = FiatWalletCard(5151515211255, "User2", 1512, 451, 12124.00)
        paymentInfoDao.addFiatWallet(fiatWalletCard1)
        paymentInfoDao.addFiatWallet(fiatWalletCard2)
        val fiatWallets = paymentInfoDao.getFiatWallets()
        Truth.assertThat(fiatWallets.size).isEqualTo(2)
        val fetchedFiatWalletByCardNumber = paymentInfoDao.getFiatWalletByCardNumber(2415634636346)
        Truth.assertThat(fetchedFiatWalletByCardNumber).isEqualTo(fiatWalletCard1)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_addCryptoCrazeVisaCard() = runBlockingTest {
        val cryptoCrazeVisaCard =
            CryptoCrazeVisaCard(123456789, 5151.6326, CryptoCrazeVisaColour.RED)
        paymentInfoDao.addCryptoCrazeVisaCard(cryptoCrazeVisaCard)
        val cryptoCrazeVisaCards = paymentInfoDao.getCryptoCrazeVisaCards()
        Truth.assertThat(cryptoCrazeVisaCards).contains(cryptoCrazeVisaCard)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_updateCryptoCrazeVisaCard() = runBlockingTest {
        var cryptoCrazeVisaCard =
            CryptoCrazeVisaCard(123456789, 5151.12, CryptoCrazeVisaColour.RED)
        paymentInfoDao.addCryptoCrazeVisaCard(cryptoCrazeVisaCard)
        var cryptoCrazeVisaCards = paymentInfoDao.getCryptoCrazeVisaCards()
        Truth.assertThat(cryptoCrazeVisaCards[0].cardId).isEqualTo(123456789)
        Truth.assertThat(cryptoCrazeVisaCards[0].balance).isEqualTo(5151.12)
        cryptoCrazeVisaCard =
            CryptoCrazeVisaCard(123456789, 12345.41, CryptoCrazeVisaColour.RED)
        paymentInfoDao.updateCryptoCrazeVisaCard(cryptoCrazeVisaCard)
        cryptoCrazeVisaCards = paymentInfoDao.getCryptoCrazeVisaCards()
        Truth.assertThat(cryptoCrazeVisaCards[0].cardId).isEqualTo(123456789)
        Truth.assertThat(cryptoCrazeVisaCards[0].balance).isEqualTo(12345.41)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_getCryptoCrazeVisaCardByCardId() = runBlockingTest {
        val cryptoCrazeVisaCard1 =
            CryptoCrazeVisaCard(123456789, 5151.23, CryptoCrazeVisaColour.RED)
        val cryptoCrazeVisaCard2 =
            CryptoCrazeVisaCard(515664366, 1516.32, CryptoCrazeVisaColour.RED)
        paymentInfoDao.addCryptoCrazeVisaCard(cryptoCrazeVisaCard1)
        paymentInfoDao.addCryptoCrazeVisaCard(cryptoCrazeVisaCard2)
        val cryptoCrazeVisaCards = paymentInfoDao.getCryptoCrazeVisaCards()
        Truth.assertThat(cryptoCrazeVisaCards.size).isEqualTo(2)
        val fetchedCryptoVisaCardById = paymentInfoDao.getCryptoCrazeVisaCardByCardId(515664366)
        Truth.assertThat(fetchedCryptoVisaCardById).isEqualTo(cryptoCrazeVisaCard2)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_deleteCryptoCrazeVisaCard() = runBlockingTest {
        val cryptoCrazeVisaCard =
            CryptoCrazeVisaCard(123456789, 5151.6326, CryptoCrazeVisaColour.RED)
        paymentInfoDao.addCryptoCrazeVisaCard(cryptoCrazeVisaCard)
        var cryptoCrazeVisaCards = paymentInfoDao.getCryptoCrazeVisaCards()
        Truth.assertThat(cryptoCrazeVisaCards.size).isEqualTo(1)
        paymentInfoDao.deleteCryptoCrazeVisaCard(cryptoCrazeVisaCard)
        cryptoCrazeVisaCards = paymentInfoDao.getCryptoCrazeVisaCards()
        Truth.assertThat(cryptoCrazeVisaCards.size).isEqualTo(0)
    }
}