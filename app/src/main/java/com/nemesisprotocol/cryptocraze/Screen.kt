package com.nemesisprotocol.cryptocraze

sealed class Screen(val route: String, val icon: Int?, val title: String) {
    object Splash : Screen("splash_screen", null, "Splash")
    object Login : Screen("login_screen", null, "Login")
    object SignUp : Screen("sign_up_screen", null, "Signup")
    object Home : Screen("home_screen", R.drawable.ic_home_24, "Home")
    object Settings : Screen("settings_screen", null, "Settings")
    object Wallet : Screen("wallet_screen", R.drawable.ic_wallet_24, "Wallet")
    object AddPaymentCard : Screen("add_payment_screen", null, "Payment Card")
    object AddCryptoCrazeVisaCard : Screen("add_crypto_craze_visa_screen", null, "Crypto Craze Visa Card")
    object Info : Screen("info_screen", R.drawable.ic_info_24, "Info")
    object CoinDetailScreen : Screen("coin_info_screen", null, "CoinInfo")
    object CryptoCrazeLogo : Screen("cryptocraze_icon", R.drawable.cryptocraze_icon, "CryptoCraze Icon")
    object CryptoCrazeVisaCardAdded : Screen("crypto_craze_visa_card_added", null, "Crypto Craze Visa Card Added")
    object PaymentCardAdded : Screen("payment_card_added", null, "Payment Card Added")
    object CryptoTransaction : Screen("crypto_transaction", null, "Crypto Transaction")
    object CryptoTransactionConfirmation : Screen("crypto_transaction_confirmation", null, "Crypto Transaction Confirmation")
    object CryptoTransactionFailed : Screen("crypto_transaction_failed", null, "Crypto Transaction Failed")
    object Portfolio : Screen("portfolio_screen", R.drawable.portfolio_icon, "Portfolio")
    object TransactionHistory : Screen("transaction_history_screen", null, "Transaction History")
    object About : Screen("about_screen", null, "About")
}
