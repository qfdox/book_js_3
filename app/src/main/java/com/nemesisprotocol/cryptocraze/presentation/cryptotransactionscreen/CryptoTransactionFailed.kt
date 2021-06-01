package com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nemesisprotocol.cryptocraze.R
import kotlinx.coroutines.delay

@Composable
fun CryptoTransactionFailed(navController: NavController, transactionType: TransactionType) {
    LaunchedEffect(key1 = true) {
        delay(2500L)
        navController.popBackStack()
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color(0xFF990F02)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.added_success_icon),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        if (transactionType == TransactionType.BUY) Text(
            text = "Purchase Failed\n\n Insufficient funds",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        else Text(text = "Sale