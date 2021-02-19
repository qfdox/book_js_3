package com.nemesisprotocol.cryptocraze.domain.paymentinfo.usecase

import com.nemesisprotocol.cryptocraze.domain.paymentinfo.FiatWalletCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.PaymentInfoRepo
import javax.inject.Inject

class AddFiatWalletUseCase @Inject constructor(private val paymentInfoRepo: PaymentInfoRepo) {
    operator fun invoke(fiatWalletCard: FiatWalletCard) =
        paymentInfoRepo