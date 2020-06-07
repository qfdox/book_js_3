
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