
package com.nemesisprotocol.cryptocraze.presentation.walletscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemesisprotocol.cryptocraze.common.DispatcherProvider
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.FiatWalletCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val addCryptoCrazeVisaCardUseCase: AddCryptoCrazeVisaCardUseCase,
    private val addFiatWalletUseCase: AddFiatWalletUseCase,
    private val updateFiatWalletUseCase: UpdateFiatWalletUseCase,
    private val updateCryptoCrazeVisaCardUseCase: UpdateCryptoCrazeVisaCardUseCase,
    private val deleteCryptoCrazeVisaCardUseCase: DeleteCryptoCrazeVisaCardUseCase,
    private val deleteFiatWalletUseCase: DeleteFiatWalletUseCase,
    private val getCryptoCrazeVisaCardsUseCase: GetCryptoCrazeVisaCardsUseCase,
    private val getCryptoCrazeVisaCardByIdUseCase: GetCryptoCrazeVisaCardByIdUseCase,
    private val getFiatWalletsUseCase: GetFiatWalletsUseCase,
    private val getFiatWalletByCardNumberUseCase: GetFiatWalletByCardNumberUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _paymentCards = MutableStateFlow<List<FiatWalletCard>>(emptyList())
    val paymentCards: StateFlow<List<FiatWalletCard>> = _paymentCards

    private val _cryptoCrazeVisaCards = MutableStateFlow<List<CryptoCrazeVisaCard>>(emptyList())
    val cryptoCrazeVisaCards: StateFlow<List<CryptoCrazeVisaCard>> = _cryptoCrazeVisaCards

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            _paymentCards.value = getFiatWalletsUseCase()
            _cryptoCrazeVisaCards.value = getCryptoCrazeVisaCardsUseCase()
        }
    }

    suspend fun getCryptoCrazeVisaCardById(cardId: Int): CryptoCrazeVisaCard {
        return withContext(dispatcherProvider.io) {
            getCryptoCrazeVisaCardByIdUseCase(cardId)
        }
    }

    suspend fun getFiatWalletByCardNumber(cardNumber: Long): FiatWalletCard {
        return withContext(dispatcherProvider.io) {
            getFiatWalletByCardNumberUseCase(cardNumber)
        }
    }

    fun addCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard) {
        viewModelScope.launch(dispatcherProvider.io) {
            addCryptoCrazeVisaCardUseCase(cryptoCrazeVisaCard)
        }
    }

    fun addFiatWallet(fiatWalletCard: FiatWalletCard) {
        viewModelScope.launch(dispatcherProvider.io) {
            addFiatWalletUseCase(fiatWalletCard)
        }
    }

    fun updateFiatWallet(fiatWalletCard: FiatWalletCard) {
        viewModelScope.launch(dispatcherProvider.io) {
            updateFiatWalletUseCase(fiatWalletCard)
        }
    }

    fun updateCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard) {
        viewModelScope.launch(dispatcherProvider.io) {
            updateCryptoCrazeVisaCardUseCase(cryptoCrazeVisaCard)
        }
    }

    fun deleteCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard) {
        viewModelScope.launch(dispatcherProvider.io) {
            deleteCryptoCrazeVisaCardUseCase(cryptoCrazeVisaCard)
        }
    }

    fun deleteFiatWallet(fiatWalletCard: FiatWalletCard) {
        viewModelScope.launch(dispatcherProvider.io) {
            deleteFiatWalletUseCase(fiatWalletCard)
        }
    }
}