
package com.nemesisprotocol.cryptocraze.domain.paymentinfo

interface PaymentInfoRepo {
    fun getFiatWallets(): List<FiatWalletCard>
    fun getFiatWalletByCardNumber(cardNumber: Long): FiatWalletCard
    fun addFiatWallet(fiatWalletCard: FiatWalletCard)
    fun updateFiatWallet(fiatWalletCard: FiatWalletCard)
    fun deleteFiatWallet(fiatWalletCard: FiatWalletCard)
    fun getCryptoCrazeVisaCards(): List<CryptoCrazeVisaCard>
    fun getCryptoCrazeVisaCardByCardId(cardId: Int): CryptoCrazeVisaCard
    fun addCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard)
    fun updateCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard)
    fun deleteCryptoCrazeVisaCard(cryptoCrazeVisaCard: CryptoCrazeVisaCard)
}