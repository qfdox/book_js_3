package com.nemesisprotocol.cryptocraze.presentation.portfolioscreen

import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nemesisprotocol.cryptocraze.extensions.roundToTwoDecimals
import com.nemesisprotocol.cryptocraze.presentation.PortfolioViewModel
import com.nemesisprotocol.cryptocraze.presentation.portfolioscreen.piechart.PieChart
import com.nemesisprotocol.cryptocraze.presentation.portfolioscreen.piechart.PieChartData
import com.nemesisprotocol.cryptocraze.presentation.portfolioscreen.piechart.PieChartData.Slice
import com.nemesisprotocol.cryptocraze.presentation.portfolioscreen.piechart.renderer.SimpleSliceDrawer
import kotlin.math.round

@Composable
fun PortfolioScreen() {

    val portfolioViewModel: PortfolioViewModel = hiltViewModel()

    val portfolio = portfolioViewModel.portfolio.collectAsState()

    var cryptoQuantity = 0.0

    val hashMap: HashMap<String, Double> = HashMap()

    for (cryptoInvestments in portfolio.value) {
        cryptoQuantity += cryptoInvestments.cryptoAmount
    }

    for (cryptoInvestments in portfolio.value) {
        val percentage = (cryptoInvestments.cryptoAmount / cryptoQuantity) * 100
        hashMap[cryptoInvestments.cryptoSymbol] = percentage
    }

    val slices = mutableListOf<Slice>()

    for ((colourIndex, i) in portfolio.value.withIndex()) {
        slices.add(Slice(round(hashMap[i.cryptoSymbol]!!.toFloat()), listOfPortfolioColours[colourIndex]))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        Text(
            text = "Your Portfolio",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center, Alignment.CenterHorizontally,
        ) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                PieChart(
                    pieChartData = PieChartData(slices),
                    modifier = Modifier
                        .padding(start = 32.dp, top = 8.dp)
                        .size(192.dp),
                    animation = TweenSpec(durationMillis = 500),
                    sliceDrawer = SimpleSliceDrawer(30f)
                )
                LazyColumn(Modifier.padding(start = 32.dp)) {
                    itemsIndexed(portfolio.value) { index, item ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 16.dp),
                            text = item.cryptoSymbol.uppercase(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = listOfPortfolioColours[index]
     