
package com.nemesisprotocol.cryptocraze.domain.portfolio

interface PortfolioRepo {
    fun getPortfolio(): List<CryptoInvestment>
    fun checkCryptoIsInvested(cryptoSymbol: String): Boolean
    fun getCryptoInvestmentBySymbol(cryptoSymbol: String): CryptoInvestment
    fun addCryptoInvestment(cryptoInvestment: CryptoInvestment)
    fun updateCryptoInvestment(cryptoInvestment: CryptoInvestment)
    fun deleteCryptoInvestment(cryptoInvestment: CryptoInvestment)
    fun wipePortfolio()
}