
package com.nemesisprotocol.cryptocraze.domain.portfolio.usecase

import com.nemesisprotocol.cryptocraze.domain.portfolio.CryptoInvestment
import com.nemesisprotocol.cryptocraze.domain.portfolio.PortfolioRepo
import javax.inject.Inject

class GetPortfolioUseCase @Inject constructor(private val portfolioRepo: PortfolioRepo) {
    operator fun invoke(): List<CryptoInvestment> = portfolioRepo.getPortfolio()
}