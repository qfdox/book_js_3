package com.nemesisprotocol.cryptocraze.domain.portfolio.usecase

import com.nemesisprotocol.cryptocraze.domain.portfolio.PortfolioRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCryptoInvestmentBySymbolUseCase @Inject constructor(private val portfolioRepo: PortfolioRepo) {
    suspend operator fun invoke(cryptoSymbol: String) =
        withContext(Dispatchers.Default) {
            portfolioRepo.getCryptoInvestm