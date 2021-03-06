package com.nemesisprotocol.cryptocraze.domain.portfolio.usecase

import com.nemesisprotocol.cryptocraze.domain.portfolio.CryptoInvestment
import com.nemesisprotocol.cryptocraze.domain.portfolio.PortfolioRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeleteCryptoInvestmentUseCase @Inject constructor(private val portfolioRepo: PortfolioRepo) {
    private val deleteCryptoInvestmentCoroutineScope = CoroutineScope(Dispatchers.Defau