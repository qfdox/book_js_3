
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