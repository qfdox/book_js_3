
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
                        textStyle = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            fontSize = 24.sp
                        )
                    )
                    if (amountOfCrypto.text.isNotEmpty()) {
                        var canParse = true
                        try {
                            amountOfCrypto.text.toDouble()
                        } catch (numberFormatException: NumberFormatException) {
                            canParse = false
                        }
                        if (canParse) {
                            val roundedAmount =
                                "%.2f".format(cryptoData.price * amountOfCrypto.text.toDouble())
                                    .toDouble()
                            Text(
                                text = "${amountOfCrypto.text} ${cryptoData.symbol.uppercase()} = £$roundedAmount",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                            )
                        }
                    }
                }
                Text(
                    text = "Select Payment method below",
                    Modifier.padding(start = 18.dp),
                    fontWeight = FontWeight.Bold
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .clickable(!cryptoCrazeVisaCardOptionSelected.value) {
                            fiatWalletOptionSelected.value = !fiatWalletOptionSelected.value
                            if (!fiatWalletOptionSelected.value) selectedFiatWallet = null
                        }
                ) {
                    Row {
                        Text(
                            text = "From Fiat Wallet",
                            Modifier
                                .padding(start = 18.dp, top = 8.dp)
                                .weight(1f),
                            fontStyle = FontStyle.Italic
                        )
                        if (fiatWalletOptionSelected.value) Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                        else Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
        if (fiatWalletOptionSelected.value) {
            items(paymentCards.value) {
                val isSelected = remember { mutableStateOf(false) }
                Card(
                    backgroundColor = if (isSelected.value) Color.Gray else MaterialTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .clickable {
                            isSelected.value = !isSelected.value
                            canPerformTransaction.value = isSelected.value
                            selectedFiatWallet = it
                        }
                ) {
                    val cardNumberAsString = it.cardNumber.toString()
                    val lastFourDigits =
                        cardNumberAsString.substring(cardNumberAsString.length - 4)
                    Text("Card ending **** **** **** $lastFourDigits", fontSize = 18.sp)
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    .clickable(!fiatWalletOptionSelected.value) {
                        cryptoCrazeVisaCardOptionSelected.value =
                            !cryptoCrazeVisaCardOptionSelected.value
                        if (!cryptoCrazeVisaCardOptionSelected.value) selectedCryptoCrazeVisaCard =
                            null
                    }
            ) {
                Row {
                    Text(
                        text = "From Crypto Craze Visa Card",
                        Modifier
                            .padding(start = 18.dp, top = 16.dp)
                            .weight(1f),
                        fontStyle = FontStyle.Italic
                    )
                    if (cryptoCrazeVisaCardOptionSelected.value) Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    else Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        if (cryptoCrazeVisaCardOptionSelected.value) {
            items(cryptoCrazeVisaCards.value) {
                val isSelected = remember { mutableStateOf(false) }
                Card(
                    backgroundColor = if (isSelected.value) Color.Gray else MaterialTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .clickable {
                            isSelected.value = !isSelected.value
                            canPerformTransaction.value = isSelected.value
                            selectedCryptoCrazeVisaCard = it
                        }
                ) {
                    Text(
                        "Crypto Craze ${it.cryptoCrazeVisaColour.name} Visa Card",
                        fontSize = 18.sp
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        cryptoTransactionDialogOpenState.value = true
                    },
                    enabled = canPerformTransaction.value && amountOfCrypto.text.isNotEmpty() && transactionType == TransactionType.BUY || canParseToDouble(amountOfCrypto.text) && cryptoInvestmentAmount.value >= amountOfCrypto.text.toDouble() && transactionType == TransactionType.SELL,
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                ) {
                    if (amountOfCrypto.text.isNotEmpty()) Text(
                        text = "$transactionTypeString ${amountOfCrypto.text} ${cryptoData.symbol.uppercase()}",
                        fontSize = 24.sp
                    )
                    else Text(
                        text = "$transactionTypeString ${cryptoData.symbol.uppercase()}",
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}

fun canParseToDouble(amountOfText: String): Boolean {
    return try {
        amountOfText.toDouble()
        true
    } catch (numberFormatException: NumberFormatException) {
        false
    }
}