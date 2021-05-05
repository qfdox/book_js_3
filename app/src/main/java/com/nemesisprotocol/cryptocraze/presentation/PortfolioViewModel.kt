package com.nemesisprotocol.cryptocraze.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemesisprotocol.cryptocraze.common.DispatcherProvider
import com.nemesisprotocol.cryptocraze.domain.portfolio.CryptoInvestment
import com.nemesisprotocol.cryptocraze.domain.portfolio.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val addCryptoInvestmentUseCase: AddCryptoInvestmentUseCase,
    private val checkCryptoIsInvestedUseCase: CheckCryptoIsInvestedUseCase,
    private val getCryptoInvestmentBySymbolUseCase: GetCryptoInvestmentBySymbolUseCase,
    private val deleteCryptoInvestmentUseCase: DeleteCryptoInvestmentUseCase,
    private val getPortfolioUseCase: GetPortfolioUseCase,
    private val updateCryptoInvestmentUseCase: UpdateCryptoInvestmentUseCase,
    private val wipePortfolioUseCase: WipePortfolioUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _portfolio = MutableState