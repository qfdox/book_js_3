
package com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoDataPriceInfo
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.FiatWalletCard
import com.nemesisprotocol.cryptocraze.domain.portfolio.CryptoInvestment
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionRecord
import com.nemesisprotocol.cryptocraze.presentation.PortfolioViewModel
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.WalletViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CryptoTransactionDialog(
    cryptoTransactionDialogOpenState: MutableState<Boolean>,
    navController: NavHostController,
    cryptoData: CryptoDataPriceInfo,
    amountOfCrypto: TextFieldValue,
    selectedFiatWallet: FiatWalletCard?,
    selectedCryptoCrazeVisaCard: CryptoCrazeVisaCard?,
    transactionType: TransactionType,
    cryptoTransactionViewModel: CryptoTransactionViewModel,
    portfolioViewModel: PortfolioViewModel,
    walletViewModel: WalletViewModel,
    coroutineScope: CoroutineScope
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        if (cryptoTransactionDialogOpenState.value) {
            val roundedAmount =
                "%.2f".format(cryptoData.price * amountOfCrypto.text.toDouble()).toDouble()
            AlertDialog(
                modifier = Modifier.wrapContentSize(),
                onDismissRequest = {
                    cryptoTransactionDialogOpenState.value = false
                },
                title = {
                    if (transactionType == TransactionType.BUY) {
                        Text(text = "Confirm Purchase")
                    } else {
                        Text(text = "Confirm Sale")
                    }
                },
                text =
                {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Row {
                            Text(
                                text = "Amount",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            )
                            Text(
                                text = "${amountOfCrypto.text} ${cryptoData.symbol.uppercase()} = £$roundedAmount",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .padding(end = 8.dp)
                            )
                        }
                        Row {
                            Text(
                                text = "Rate",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            )
                            Text(
                                text = "1 ${cryptoData.symbol.uppercase()} = £${cryptoData.price}",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .padding(end = 8.dp)
                            )
                        }

                        Row {
                            Text(
                                text = "Method",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            )
                            if (selectedFiatWallet != null) {
                                val cardNumberAsString = selectedFiatWallet.cardNumber.toString()
                                val lastFourDigits =
                                    cardNumberAsString.substring(cardNumberAsString.length - 4)
                                Text(
                                    text = "Card Ending $lastFourDigits",
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterVertically)
                                        .padding(end = 8.dp)
                                )
                            } else if (selectedCryptoCrazeVisaCard != null) {
                                Text(
                                    text = "CC Visa Card ${selectedCryptoCrazeVisaCard.cryptoCrazeVisaColour}",
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterVertically)
                                        .padding(end = 8.dp)
                                )
                            }
                        }

                        Row {
                            if (transactionType == TransactionType.BUY) {
                                Text(
                                    text = "Total Cost",
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterVertically)
                                        .weight(1f)
                                        .padding(start = 8.dp)
                                )
                            } else {
                                Text(
                                    text = "Total Sale",
                                    modifier = Modifier
                                        .align(alignment = Alignment.CenterVertically)
                                        .weight(1f)
                                        .padding(start = 8.dp)
                                )
                            }
                            Text(text = "£$roundedAmount")
                        }
                    }
                },
                confirmButton = {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            if (selectedFiatWallet != null) {
                                if (selectedFiatWallet.balance > roundedAmount || transactionType == TransactionType.SELL) {
                                    val updatedFiatWallet = FiatWalletCard(
                                        selectedFiatWallet.cardNumber,
                                        selectedFiatWallet.cardName,
                                        selectedFiatWallet.expiryNumber,
                                        selectedFiatWallet.cvvNumber,
                                        if (transactionType == TransactionType.BUY) selectedFiatWallet.balance - roundedAmount else selectedFiatWallet.balance + roundedAmount
                                    )
                                    walletViewModel.updateFiatWallet(updatedFiatWallet)
                                    cryptoTransactionViewModel.addTransactionRecord(
                                        TransactionRecord(
                                            cryptoSymbol = cryptoData.symbol.uppercase(),
                                            cryptoAmount = amountOfCrypto.text.toDouble(),
                                            amount = "£$roundedAmount",
                                            transactionType = transactionType
                                        )
                                    )
                                    coroutineScope.launch(Dispatchers.IO) {
                                        if (!portfolioViewModel.checkCryptoIsInvested(cryptoData.symbol.uppercase())) {
                                            portfolioViewModel.addCryptoInvestment(
                                                CryptoInvestment(
                                                    cryptoSymbol = cryptoData.symbol.uppercase(),
                                                    cryptoName = cryptoData.name,
                                                    cryptoAmount = amountOfCrypto.text.toDouble()
                                                )
                                            )
                                        } else {
                                            val cryptoInvestment =
                                                portfolioViewModel.getCryptoInvestmentBySymbol(
                                                    cryptoData.symbol.uppercase()
                                                )
                                            portfolioViewModel.updateCryptoInvestment(
                                                CryptoInvestment(
                                                    cryptoSymbol = cryptoData.symbol.uppercase(),
                                                    cryptoName = cryptoData.name,
                                                    cryptoAmount = if (transactionType == TransactionType.BUY) cryptoInvestment.cryptoAmount + amountOfCrypto.text.toDouble() else cryptoInvestment.cryptoAmount - amountOfCrypto.text.toDouble()
                                                )
                                            )
                                        }
                                    }
                                    cryptoTransactionDialogOpenState.value = false
                                    navController.navigate(Screen.CryptoTransactionConfirmation.route + "/$transactionType")
                                } else {
                                    if (transactionType == TransactionType.BUY) {
                                        cryptoTransactionDialogOpenState.value = false
                                        navController.navigate(Screen.CryptoTransactionFailed.route + "/$transactionType")
                                    } else {
                                        cryptoTransactionDialogOpenState.value = false
                                        navController.navigate(Screen.CryptoTransactionConfirmation.route + "/$transactionType")
                                    }
                                }
                            } else {
                                if (selectedCryptoCrazeVisaCard!!.balance > roundedAmount || transactionType == TransactionType.SELL) {
                                    val updatedCryptoCrazeVisaCard = CryptoCrazeVisaCard(
                                        selectedCryptoCrazeVisaCard.cardId,
                                        if (transactionType == TransactionType.BUY) selectedCryptoCrazeVisaCard.balance - roundedAmount else selectedCryptoCrazeVisaCard.balance + roundedAmount,
                                        selectedCryptoCrazeVisaCard.cryptoCrazeVisaColour
                                    )
                                    walletViewModel.updateCryptoCrazeVisaCard(
                                        updatedCryptoCrazeVisaCard
                                    )
                                    cryptoTransactionViewModel.addTransactionRecord(
                                        TransactionRecord(
                                            cryptoSymbol = cryptoData.symbol.uppercase(),
                                            cryptoAmount = amountOfCrypto.text.toDouble(),
                                            amount = "£$roundedAmount",
                                            transactionType = transactionType
                                        )
                                    )
                                    coroutineScope.launch(Dispatchers.IO) {
                                        if (!portfolioViewModel.checkCryptoIsInvested(cryptoData.symbol.uppercase()) && transactionType == TransactionType.BUY) {
                                            portfolioViewModel.addCryptoInvestment(
                                                CryptoInvestment(
                                                    cryptoSymbol = cryptoData.symbol.uppercase(),
                                                    cryptoName = cryptoData.name,
                                                    cryptoAmount = amountOfCrypto.text.toDouble()
                                                )
                                            )
                                        } else {
                                            val cryptoInvestment =
                                                portfolioViewModel.getCryptoInvestmentBySymbol(
                                                    cryptoData.symbol.uppercase()
                                                )
                                            portfolioViewModel.updateCryptoInvestment(
                                                CryptoInvestment(
                                                    cryptoSymbol = cryptoData.symbol.uppercase(),
                                                    cryptoName = cryptoData.name,
                                                    cryptoAmount = if (transactionType == TransactionType.BUY) cryptoInvestment.cryptoAmount + amountOfCrypto.text.toDouble() else cryptoInvestment.cryptoAmount - amountOfCrypto.text.toDouble()
                                                )
                                            )
                                        }
                                    }
                                    cryptoTransactionDialogOpenState.value = false
                                    navController.navigate(Screen.CryptoTransactionConfirmation.route + "/$transactionType")
                                } else {
                                    if (transactionType == TransactionType.BUY) {
                                        cryptoTransactionDialogOpenState.value = false
                                        navController.navigate(Screen.CryptoTransactionFailed.route + "/$transactionType")
                                    } else {
                                        cryptoTransactionDialogOpenState.value = false
                                        navController.navigate(Screen.CryptoTransactionConfirmation.route + "/$transactionType")
                                    }
                                }
                            }
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            cryptoTransactionDialogOpenState.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }, shape = RoundedCornerShape(30.dp)
                )
            }
        }
    }
    