
package com.nemesisprotocol.cryptocraze

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.nemesisprotocol.cryptocraze.data.database.portfolio.PortfolioDao
import com.nemesisprotocol.cryptocraze.data.database.portfolio.PortfolioDatabase
import com.nemesisprotocol.cryptocraze.domain.portfolio.CryptoInvestment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class PortfolioDatabaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_portfolio_db")
    lateinit var database: PortfolioDatabase
    private lateinit var portfolioDao: PortfolioDao

    @Before
    fun setup() {
        hiltRule.inject()
        portfolioDao = database.portfolioDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_addCryptoInvestment() = runBlockingTest {
        val cryptoInvestment = CryptoInvestment("BTC", "Bitcoin", 6.0)
        portfolioDao.addCryptoInvestment(cryptoInvestment)
        val portfolio = portfolioDao.getPortfolio()
        Truth.assertThat(portfolio).contains(cryptoInvestment)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_updateCryptoInvestment() = runBlockingTest {
        var cryptoInvestment = CryptoInvestment("BTC", "Bitcoin", 6.0)
        portfolioDao.addCryptoInvestment(cryptoInvestment)
        var portfolio = portfolioDao.getPortfolio()
        Truth.assertThat(portfolio[0].cryptoAmount).isEqualTo(6.0)
        cryptoInvestment = CryptoInvestment("BTC", "Bitcoin", 2.0)
        portfolioDao.updateCryptoInvestment(cryptoInvestment)
        portfolio = portfolioDao.getPortfolio()
        Truth.assertThat(portfolio[0].cryptoAmount).isEqualTo(2.0)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_deleteCryptoInvestment() = runBlockingTest {
        val cryptoInvestment = CryptoInvestment("BTC", "Bitcoin", 6.0)
        portfolioDao.addCryptoInvestment(cryptoInvestment)
        var portfolio = portfolioDao.getPortfolio()
        Truth.assertThat(portfolio.size).isEqualTo(1)
        portfolioDao.deleteCryptoInvestment(cryptoInvestment)
        portfolio = portfolioDao.getPortfolio()
        Truth.assertThat(portfolio.size).isEqualTo(0)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_checkCryptoIsInvested() = runBlockingTest {
        val cryptoInvestment = CryptoInvestment("BTC", "Bitcoin", 6.0)
        portfolioDao.addCryptoInvestment(cryptoInvestment)
        var isCryptoInvested = portfolioDao.checkCryptoIsInvested("BTC")
        Truth.assertThat(isCryptoInvested).isEqualTo(true)
        isCryptoInvested = portfolioDao.checkCryptoIsInvested("ETH")
        Truth.assertThat(isCryptoInvested).isEqualTo(false)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_getCryptoInvestmentBySymbol() = runBlockingTest {
        val cryptoInvestment1 = CryptoInvestment("BTC", "Bitcoin", 6.0)
        val cryptoInvestment2 = CryptoInvestment("ETH", "Ethereum ", 12.0)
        portfolioDao.addCryptoInvestment(cryptoInvestment1)
        portfolioDao.addCryptoInvestment(cryptoInvestment2)
        val portfolio = portfolioDao.getPortfolio()
        Truth.assertThat(portfolio.size).isEqualTo(2)
        val fetchedCryptoInvestmentBySymbol = portfolioDao.getCryptoInvestmentBySymbol("BTC")
        Truth.assertThat(fetchedCryptoInvestmentBySymbol).isEqualTo(cryptoInvestment1)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_wipePortfolio() = runBlockingTest {
        for (i in 1..100) portfolioDao.addCryptoInvestment(CryptoInvestment("cryptoSymbol$i", "cryptoName$i", i.toDouble()))
        var portfolio = portfolioDao.getPortfolio()
        Truth.assertThat(portfolio.size).isEqualTo(100)
        portfolioDao.wipePortfolio()
        portfolio = portfolioDao.getPortfolio()
        Truth.assertThat(portfolio.size).isEqualTo(0)
    }
}