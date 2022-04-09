package com.nemesisprotocol.cryptocraze.presentation.walletscreen.addwallet

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nemesisprotocol.cryptocraze.R
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.domain.paymentinfo.CryptoCrazeVisaCard
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.CryptoCrazeVisaColour
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.WalletViewModel

@ExperimentalAnimationApi
@Composable
fun AddCryptoCrazeVisaCardScreen(
    navController: NavHostController,
    cryptoCrazeVisaCard: CryptoCrazeVisaCard?
) {
    val walletViewModel: WalletViewModel = hiltViewModel()
    val cardColour =
        remember {
            mutableStateOf(
                cryptoCrazeVisaCard?.cryptoCrazeVisaColour
                    ?: CryptoCrazeVisaColour.BLACK
            )
        }

    Column(modifier = Modifier.fillMaxSize()) {
        CryptoCrazeVisaCard(cardColour)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.crypto_craze_visa_card_black),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clickable { cardColour.value = CryptoCrazeVisaColour.BLACK },
                )
                Image(
                    painter