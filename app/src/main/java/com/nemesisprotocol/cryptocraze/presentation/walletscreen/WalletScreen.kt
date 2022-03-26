package com.nemesisprotocol.cryptocraze.presentation.walletscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nemesisprotocol.cryptocraze.R
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.addwallet.AddCryptoCrazeVisaCardDialog
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.addwallet.AddPaymentCardDialog

@Composable
fun WalletScreen(navController: NavHostController) {
    val addPaymentCardDialog = remember { mutableStateOf(false) }
    val addCryptoCrazeVisaCardDialog = remember { mutableStateOf(false) }
    AddPaymentCardDialog(addPaymentCardDialog, navController)
    AddCryptoCrazeVisaCardDialog(addCryptoCrazeVisaCardDialog, navController)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .clickable {
                    addCryptoCrazeVisaCardDialog.value = true
                }
        ) {
            Row {
                Text(
                    text = "Crypto Craze Visa Card",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.visa_card_icon),
                    contentDescription = null,
                    Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(end = 8.dp)
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .clickable {
                    addPaymentCardDialog.value = true
                }
        ) {
            Row {
                Text(
                    text = "Fiat Wallet",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_wallet_24),
                