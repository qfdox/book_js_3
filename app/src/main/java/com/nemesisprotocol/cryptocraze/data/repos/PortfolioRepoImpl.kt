package com.nemesisprotocol.cryptocraze.data.repos

import com.nemesisprotocol.cryptocraze.data.database.portfolio.PortfolioDao
import com.nemesisprotocol.cryptocraze.domain.portfolio.CryptoInvestment
import com.nemesisprotocol.cryptocraze.domain.portfolio.PortfolioRepo
import javax.inject.Inject

class PortfolioRepoImpl @Inject constructor(private val portfolioDao: PortfolioDao) :
    PortfolioRepo {

    override fun getPortfolio(): List<CryptoInvestment> {
        return portfolioDao.getPortfolio()
    }

    override fun checkCryptoIsInvested(cryptoSymbol: String): Boolean {
        return portfolioDao.checkCryptoIsInvested(cryptoSymbol = cryptoSymbol)
    }

    override fun getCryptoInvestmentBySymbol(cryptoSymbol: String): CryptoInvestment {
        return portfolioDao.getCryptoInvestmentBySymbol(cryptoSymbol = cryptoSymbol)
    }

    override fun addCryptoInvestment(cryptoInvestment: CryptoInvestment) {
        portfolioDao.addCryptoInvestment(cryptoInvestment = cryptoInvestment)
    }

    override fun updateCryptoInvestment(cryptoInvestment: CryptoInvestment) {
        portfolioDao.updateCryptoInvestment(cryptoInvestment = cryptoInvestment)
    }

    override fun deleteCryptoInvestment(cryptoInvestment: CryptoInvestment) {
        portfolioDao.deleteCryptoInvestment(cryptoInvestment = cryptoInvestment)
    }

    override fun wipePortfolio() {
        portfolioDao.wipePortfolio()
    }
}
