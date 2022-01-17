
package com.nemesisprotocol.cryptocraze.presentation.transactionhistoryscreen

import android.icu.text.CompactDecimalFormat
import android.icu.text.CompactDecimalFormat.CompactStyle
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen.CryptoTransactionViewModel
import java.util.*

@Composable
fun TransactionHistoryScreen() {
    val cryptoTransactionViewModel: CryptoTransactionViewModel = hiltViewModel()
    val transactionHistory = cryptoTransactionViewModel.transactionHistory.collectAsState()
    val columnWeight1 = .20f
    val columnWeight2 = .25f
    val columnWeight3 = .40f
    val pattern = "dd-MM-yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
    val cdfShort: CompactDecimalFormat =
        CompactDecimalFormat.getInstance(Locale.UK, CompactStyle.SHORT)
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Transaction History",
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        LazyColumn(Modifier.wrapContentSize()) {
            item {
                Row(Modifier.background(Color.Gray)) {
                    TableCell(text = "Symbol", weight = columnWeight1)
                    TableCell(text = "Quantity", weight = columnWeight2)
                    TableCell(text = "Amount", weight = columnWeight3)
                    TableCell(text = "Date", weight = columnWeight3)
                    TableCell(text = "Type", weight = columnWeight1)
                }
            }
            items(transactionHistory.value) {
                Row(Modifier.fillMaxWidth()) {
                    TableCell(text = it.cryptoSymbol, weight = columnWeight1)
                    TableCell(text = cdfShort.format(it.cryptoAmount), weight = columnWeight2)
                    TableCell(
                        text = "£${cdfShort.format(it.amount.substring(1).toDouble())}",
                        weight = columnWeight3
                    )
                    TableCell(text = simpleDateFormat.format(it.timestamp), weight = columnWeight3)
                    TableCell(text = it.transactionType.toString(), weight = columnWeight1)
                }
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .wrapContentWidth()
    )
}