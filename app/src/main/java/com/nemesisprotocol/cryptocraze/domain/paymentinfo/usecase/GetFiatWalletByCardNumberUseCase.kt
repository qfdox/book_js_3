package com.nemesisprotocol.cryptocraze.domain.paymentinfo.usecase

import com.nemesisprotocol.cryptocraze.domain.paymentinfo.FiatWalletCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.PaymentInfoRepo
import javax.inject.Inject

class GetFiatWalletByCardNumberUseCase @Inject constructor(private val paymentInfoRepo: PaymentInfoRepo) {
    operator fun invoke(cardNumber: Long): FiatWalletCard =
        paymentInfoRepo.getFiatWalletByCardNumber(cardNumber = cardNum