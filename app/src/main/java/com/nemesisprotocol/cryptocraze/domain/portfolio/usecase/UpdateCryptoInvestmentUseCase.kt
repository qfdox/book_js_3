
package com.nemesisprotocol.cryptocraze.domain.portfolio.usecase

import com.nemesisprotocol.cryptocraze.domain.portfolio.CryptoInvestment
import com.nemesisprotocol.cryptocraze.domain.portfolio.PortfolioRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateCryptoInvestmentUseCase @Inject constructor(private val portfolioRepo: PortfolioRepo) {
    private val updateCryptoInvestmentCoroutineScope = CoroutineScope(Dispatchers.Default)
    operator fun invoke(cryptoInvestment: CryptoInvestment) =
        updateCryptoInvestmentCoroutineScope.launch {
            portfolioRepo.updateCryptoInvestment(cryptoInvestment)
        }
}