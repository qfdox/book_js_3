
package com.nemesisprotocol.cryptocraze

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.nemesisprotocol.cryptocraze.data.database.cryptofavdata.CryptoFavDataDao
import com.nemesisprotocol.cryptocraze.data.database.cryptofavdata.CryptoFavDataDatabase
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoData
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
class CryptoDataDatabaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_crypto_fav_data_db")
    lateinit var database: CryptoFavDataDatabase
    private lateinit var cryptoFavDataDao: CryptoFavDataDao

    @Before
    fun setup() {
        hiltRule.inject()
        cryptoFavDataDao = database.cryptoFavDataDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_addFav() = runBlockingTest {
        val cryptoData = CryptoData(
            symbol = "BTC",
            price = 5151.51,
            name = "Bitcoin",
            image = "image",
            dailyChange = 64.36,
            dailyChangePercentage = 45.00,
            high = 6436.41,
            low = 1122.53,
            marketCap = 7331,
            volume = 41.50,
            supply = 12.61,
            chartData = listOf()
        )
        cryptoFavDataDao.addFav(cryptoData)
        val favData = cryptoFavDataDao.getFavCryptos().getOrAwaitValue()
        Truth.assertThat(favData).contains(cryptoData)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_removeFav() = runBlockingTest {
        val cryptoData = CryptoData(
            symbol = "BTC",
            price = 5151.51,
            name = "Bitcoin",
            image = "image",
            dailyChange = 64.36,
            dailyChangePercentage = 45.00,
            high = 6436.41,
            low = 1122.53,
            marketCap = 7331,
            volume = 41.50,
            supply = 12.61,
            chartData = listOf()
        )
        cryptoFavDataDao.addFav(cryptoData)
        var favData = cryptoFavDataDao.getFavCryptos().getOrAwaitValue()
        Truth.assertThat(favData.size).isEqualTo(1)
        cryptoFavDataDao.removeFav(cryptoData)
        favData = cryptoFavDataDao.getFavCryptos().getOrAwaitValue()
        Truth.assertThat(favData.size).isEqualTo(0)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_checkFavCryptoExists() = runBlockingTest {
        val cryptoData = CryptoData(
            symbol = "BTC",
            price = 5151.51,
            name = "Bitcoin",
            image = "image",
            dailyChange = 64.36,
            dailyChangePercentage = 45.00,
            high = 6436.41,
            low = 1122.53,
            marketCap = 7331,
            volume = 41.50,
            supply = 12.61,
            chartData = listOf()
        )
        cryptoFavDataDao.addFav(cryptoData)
        var favCryptoExists = cryptoFavDataDao.checkFavCryptoExists("Bitcoin")
        Truth.assertThat(favCryptoExists).isEqualTo(true)
        favCryptoExists = cryptoFavDataDao.checkFavCryptoExists("Ethereum")
        Truth.assertThat(favCryptoExists).isEqualTo(false)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_wipeFavorites() = runBlockingTest {
        val cryptoData1 = CryptoData(
            symbol = "BTC",
            price = 5151.51,
            name = "Bitcoin",
            image = "image",
            dailyChange = 64.36,
            dailyChangePercentage = 45.00,
            high = 6436.41,
            low = 1122.53,
            marketCap = 7331,
            volume = 41.50,
            supply = 12.61,
            chartData = listOf()
        )
        val cryptoData2 = CryptoData(
            symbol = "ETH",
            price = 1241.51,
            name = "Ethereum",
            image = "image",
            dailyChange = 53.36,
            dailyChangePercentage = 14.00,
            high = 2622.41,
            low = 2141.53,
            marketCap = 1415,
            volume = 636.50,
            supply = 535.61,
            chartData = listOf()
        )
        cryptoFavDataDao.addFav(cryptoData1)
        cryptoFavDataDao.addFav(cryptoData2)
        var favCryptoExists = cryptoFavDataDao.checkFavCryptoExists("Bitcoin")
        Truth.assertThat(favCryptoExists).isEqualTo(true)
        favCryptoExists = cryptoFavDataDao.checkFavCryptoExists("Ethereum")
        Truth.assertThat(favCryptoExists).isEqualTo(true)
        var favData = cryptoFavDataDao.getFavCryptos().getOrAwaitValue()
        Truth.assertThat(favData.size).isEqualTo(2)
        cryptoFavDataDao.wipeFavorites()
        favData = cryptoFavDataDao.getFavCryptos().getOrAwaitValue()
        Truth.assertThat(favData.size).isEqualTo(0)
    }
}