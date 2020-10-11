package com.nemesisprotocol.cryptocraze.data.database.portfolio

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nemesisprotocol.cryptocraze.domain.portfolio.CryptoInvestment

@Dao
interface PortfolioDao {

    @Query("SELECT * FROM crypto_investments")
    fun getPortfolio(): List<CryptoInvestment>

    @Query("SELECT EXISTS(SELECT * FROM crypto_investments WHERE cryptoSymbol = :cryptoSymbol)")
    fun checkCryptoIsInvested(cryptoSymbol: String): Boolean

    @Query("SELECT * FROM crypto_investments WHERE cryptoSymbol =:cryptoSymbol")
    fun getCryptoInvestmentBySymbol(cryptoSymbol: String): CryptoInvestment

    @Insert
    fun addCryptoInvestment(cryptoInvestment: CryptoInvestment)

    @Update
    fun updateCryptoInvestment(cryptoInvestment: CryptoInvestment)

    @Delete
    fun deleteCryptoInvestment(cryptoInvestment: CryptoInvestment)

    @Query("DELETE FROM crypto_investments")
    fun wipePortfolio()
}
