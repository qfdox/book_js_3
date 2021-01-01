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
        return portfolioDao.getCryptoInvestm