package com.nemesisprotocol.cryptocraze.presentation.walletscreen.addwallet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.WalletViewModel

@Composable
fun AddPaymentCardDialog(
    addPaymentCardDialog: MutableState<Boolean>,
    navController: NavHostController,

) {
    val walletViewModel: WalletViewModel = hiltViewModel()
    val paymentCards = walletViewModel.paymentCards.collectAsState()

    Column {
        if (addPaymentCardDialog.value) {
            AlertDialog(
                modifier = Modifier.wrapContentSize(),
                onDismissRequest = {
                    addPaymentCardDialog.value = false
                },
                title = {
                    Text(text = "Your Fiat Wallet")
                },
                text = {
                    if (paymentCards.value.isNotEmpty()) {
                        LazyColumn {
        