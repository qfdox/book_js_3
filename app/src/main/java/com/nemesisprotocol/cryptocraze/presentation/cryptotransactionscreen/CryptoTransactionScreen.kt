
package com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoDataPriceInfo
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.FiatWalletCard
import com.nemesisprotocol.cryptocraze.presentation.PortfolioViewModel
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.WalletViewModel
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.addwallet.InputItem
import kotlinx.coroutines.Dispatchers

@Composable
fun CryptoTransactionScreen(
    cryptoData: CryptoDataPriceInfo,
    transactionType: TransactionType,
    navController: NavHostController
) {
    val walletViewModel: WalletViewModel = hiltViewModel()
    val cryptoTransactionViewModel: CryptoTransactionViewModel = hiltViewModel()
    val portfolioViewModel: PortfolioViewModel = hiltViewModel()
    val cryptoTransactionDialogOpenState = remember { mutableStateOf(false) }
    val paymentCards = walletViewModel.paymentCards.collectAsState()
    val cryptoCrazeVisaCards = walletViewModel.cryptoCrazeVisaCards.collectAsState()
    val fiatWalletOptionSelected = remember { mutableStateOf(false) }
    val cryptoCrazeVisaCardOptionSelected = remember { mutableStateOf(false) }
    val canPerformTransaction = remember { mutableStateOf(false) }
    var amountOfCrypto by remember { mutableStateOf(TextFieldValue()) }
    var selectedFiatWallet: FiatWalletCard? by remember { mutableStateOf(null) }
    var selectedCryptoCrazeVisaCard: CryptoCrazeVisaCard? by remember { mutableStateOf(null) }
    val transactionTypeString =
        Character.toUpperCase(transactionType.name[0]) + transactionType.name.lowercase()
            .substring(1)
    val coroutineScope = rememberCoroutineScope()
    val cryptoInvestmentAmount = remember { mutableStateOf(0.00) }

    if (transactionType == TransactionType.SELL) {
        LaunchedEffect(Dispatchers.IO) {
            if (portfolioViewModel.checkCryptoIsInvested(cryptoData.symbol.uppercase())) {
                val cryptoInvestment =
                    portfolioViewModel.getCryptoInvestmentBySymbol(cryptoData.symbol.uppercase())
                cryptoInvestmentAmount.value = cryptoInvestment.cryptoAmount
            }
        }
    }

    CryptoTransactionDialog(
        cryptoTransactionDialogOpenState = cryptoTransactionDialogOpenState,
        navController = navController,
        cryptoData = cryptoData,
        amountOfCrypto = amountOfCrypto,
        selectedFiatWallet = selectedFiatWallet,
        selectedCryptoCrazeVisaCard = selectedCryptoCrazeVisaCard,
        transactionType = transactionType,
        cryptoTransactionViewModel = cryptoTransactionViewModel,
        portfolioViewModel = portfolioViewModel,
        walletViewModel = walletViewModel,
        coroutineScope = coroutineScope
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
            ) {
                Text(
                    text = "$transactionTypeString ${cryptoData.symbol.uppercase()}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = "Enter how much ${cryptoData.symbol.uppercase()} you would like to $transactionTypeString",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    InputItem(
                        textFieldValue = amountOfCrypto,
                        label = "",
                        keyboardType = KeyboardType.Number,
                        onTextChanged = { amountOfCrypto = it },
                        modifier = Modifier
                            .padding(start = 32.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
                            .fillMaxWidth(),