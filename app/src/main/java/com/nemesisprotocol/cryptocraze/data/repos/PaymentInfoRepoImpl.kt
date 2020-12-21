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

    override fun getFiatWalletByCardNumber(cardNumber: Long): FiatWalletCard {
        return paymentInfoDao.getFiatWalletByCardNumber(cardNumber = cardNumber)
    }

    override fun addFiatWallet(fiatWalletCard: FiatWalletCard) {
        paymentInfoDao.addFiatWallet(fiatWalletCard = fiatWalletCard)
    }

    override fun updateFiatWallet(fiatWalletCard: FiatWalletCard) {
        paymentInfoDao.updateFiatWallet(fiatWalletCard = fiatWalletCard)
    }

    override fun deleteFiatWallet(fiatWalletCard: FiatWalletCard) {
        paymentInfoDao.deleteFiatWallet(fiatWalletCard = fiatWalletCard)
    }

    override fun getCryptoCrazeVisaCards(): List<CryptoCrazeVisaCard> {
        return paymentInfoDao.getCryptoCrazeVisaCards()
    }

    override fun getCryptoCrazeVisaCardByCardId(cardId: Int): CryptoCrazeVisaCard {
        return paymentInfoDao.getCryptoCrazeVisaCardByCardId(cardId = cardId)
    }

    override fun addCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard) {
        paymentInfoDao.addCryptoCrazeVisaCard(cryptoCrazeVisaCard = cryptoCrazeVisaCard)
    }

    override fun updateCryptoCrazeVisaCard(cry