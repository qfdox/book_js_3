
package com.nemesisprotocol.cryptocraze.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.gson.Gson
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoDataPriceInfo
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.FiatWalletCard
import com.nemesisprotocol.cryptocraze.presentation.aboutscreen.AboutScreen
import com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen.CryptoTransactionConfirmation
import com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen.CryptoTransactionFailed
import com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen.CryptoTransactionScreen
import com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen.TransactionType
import com.nemesisprotocol.cryptocraze.presentation.homescreen.HomeScreen
import com.nemesisprotocol.cryptocraze.presentation.homescreen.HomeViewModel
import com.nemesisprotocol.cryptocraze.presentation.infoscreen.InfoScreen
import com.nemesisprotocol.cryptocraze.presentation.infoscreen.coininfo.CoinInfoScreen
import com.nemesisprotocol.cryptocraze.presentation.login.loginscreen.LoginScreen
import com.nemesisprotocol.cryptocraze.presentation.login.signupscreen.SignUpScreen
import com.nemesisprotocol.cryptocraze.presentation.portfolioscreen.PortfolioScreen
import com.nemesisprotocol.cryptocraze.presentation.settings.SettingsScreen
import com.nemesisprotocol.cryptocraze.presentation.splashscreen.SplashScreen
import com.nemesisprotocol.cryptocraze.presentation.transactionhistoryscreen.TransactionHistoryScreen
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.CryptoCrazeVisaCardAddedScreen
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.PaymentCardAddedScreen
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.WalletScreen
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.addwallet.AddCryptoCrazeVisaCardScreen
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.addwallet.AddPaymentScreen

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(
    navController: NavHostController,
    userLoggedIn: MutableState<Boolean>,
    homeViewModel: HomeViewModel,
    currentRoute: MutableState<String>
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            currentRoute.value = Screen.Splash.route
            SplashScreen(navController = navController)
        }

        composable(Screen.Login.route) {
            currentRoute.value = Screen.Login.route
            LoginScreen(userLoggedIn = userLoggedIn, navController = navController)
        }

        composable(Screen.SignUp.route) {
            currentRoute.value = Screen.SignUp.route
            SignUpScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            currentRoute.value = Screen.Home.route
            HomeScreen(homeViewModel)
        }

        composable(Screen.Wallet.route) {
            currentRoute.value = Screen.Wallet.route
            WalletScreen(navController)
        }

        composable(Screen.Info.route) {
            currentRoute.value = Screen.Info.route
            InfoScreen(navController = navController)
        }

        composable(Screen.CoinDetailScreen.route + "/{coinId}") {
            currentRoute.value = Screen.CoinDetailScreen.route
            CoinInfoScreen()
        }

        composable(
            Screen.AddPaymentCard.route + "/{savedFiatWalletCard}",
            arguments = listOf(
                navArgument("savedFiatWalletCard") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("savedFiatWalletCard").let { json ->
                val fiatWalletCard = Gson().fromJson(json, FiatWalletCard::class.java)
                AddPaymentScreen(navController, fiatWalletCard)
            }
            currentRoute.value = Screen.AddPaymentCard.route
        }

        composable(
            Screen.AddCryptoCrazeVisaCard.route + "/{savedCryptoCrazeVisaCard}",
            arguments = listOf(
                navArgument("savedCryptoCrazeVisaCard") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("savedCryptoCrazeVisaCard").let { json ->
                val cryptoCrazeVisaCard = Gson().fromJson(json, CryptoCrazeVisaCard::class.java)
                AddCryptoCrazeVisaCardScreen(navController, cryptoCrazeVisaCard)
            }
            currentRoute.value = Screen.AddCryptoCrazeVisaCard.route
        }

        composable(Screen.CryptoCrazeVisaCardAdded.route) {
            currentRoute.value = Screen.CryptoCrazeVisaCardAdded.route
            CryptoCrazeVisaCardAddedScreen(navController)
        }

        composable(Screen.PaymentCardAdded.route) {
            currentRoute.value = Screen.PaymentCardAdded.route
            PaymentCardAddedScreen(navController)
        }

        composable(
            Screen.CryptoTransaction.route + "/{cryptoDataPriceInfo}/{transactionType}",
            arguments = listOf(
                navArgument("cryptoDataPriceInfo") {
                    type = NavType.StringType
                },
                navArgument("transactionType") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val transactionType = backStackEntry.arguments?.getString("transactionType")
            backStackEntry.arguments?.getString("cryptoDataPriceInfo").let { json ->
                val cryptoData = Gson().fromJson(json, CryptoDataPriceInfo::class.java)
                CryptoTransactionScreen(
                    cryptoData,
                    TransactionType.valueOf(transactionType!!),
                    navController
                )
            }
            currentRoute.value = Screen.CryptoTransaction.route
        }

        composable(
            Screen.CryptoTransactionConfirmation.route + "/{transactionType}",
            arguments = listOf(
                navArgument("transactionType") {
                    type = NavType.StringType
                }
            )