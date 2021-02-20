package com.nemesisprotocol.cryptocraze.domain.paymentinfo.usecase

import com.nemesisprotocol.cryptocraze.domain.paymentinfo.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.PaymentInfoRepo
import javax.inject.Inject

class DeleteCryptoCrazeVisaCardUseCase @Inject constructor(private val paymentInfoRepo: PaymentInfoRepo) {
    operator fun invoke(cryptoCrazeVisaCard: CryptoCrazeVisaCard) =
        paymentInfoRepo.deleteCryptoCrazeVisaCard(cryptoCrazeVisaCard)
}
