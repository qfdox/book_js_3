package com.nemesisprotocol.cryptocraze.data.repos

import com.nemesisprotocol.cryptocraze.data.database.paymentinfo.PaymentInfoDao
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.FiatWalletCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.PaymentInfoRepo
import javax.inject.Inject

class PaymentInfoRepoImpl @Inject constructor(private val paymentInfoDao: PaymentInfoDao) :
    PaymentInfoRepo {

    override fun getFiatWallets(): List<FiatWalletCard> {
        return paymentInfoDao.getFiatWallets()
    }

    override fun getFiatWalletByCardNumber(cardNumber: Long): Fiat