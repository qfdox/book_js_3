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
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            text = "${hashMap[item.cryptoSymbol]!!.roundToTwoDecimals()}%",
                            fontStyle = FontStyle.Italic,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

val listOfPortfolioColours = listOf(
    Color(0xFFFFFF00),
    Color(0xFFD51652),
    Color(0xFFC543DB),
    Color(0xFF35E4FA),
    Color(0xFF133D30),
    Color(0xFF912F22),
    Color(0xFF4C18A8),
    Color(0xFF00FF0D),
    Color(0xFFBD9161),
    Color(0xFFA519AA),
    Color(0xFF348F2D),
    Color(0xFF7C7C0B),
    Color(0xFFF44336),
    Color(0xFF673AB7),
    Color(0xFF3F51B5),
    Color(0xFF2196F3),
    Color(0xFF009688),
    Color(0xFF4CAF50),
    Color(0xFFFF9100),
    Color(0xFF08356D),
    Color(0xFFAC614A),
    Color(0xFFDBD960),
    Color(0xFF044B53),
    Color(0xFFFF5100),
    Color(0xFFF5A169),
    Color(0xFF0A2D31),
    Color(0xFFA77B40),
    Color(0xFF56D171),
    Color(0xFF3ACC88),
    Color(0xFF144D8A),
    Color(0xFF4D4D0C),
    Color(0xFF87ACEB),
    Color(0xFF3F8060),
    Color(0xFF281A47),
    Color(0xFF344D17),
    Color(0xFF43126F),
    Color(0xFF0819AF),
    Color(0xFF099709),
    Color(0xFF160466),
    Color(0xFF154127),
    Color(0xFF95E643),
    Color(0xFF744791),
    Color(0xFF2A94A0),
    Color(0xFF5D9B7D),
    Color(0xFF741C1C),
    Color(0xFFBDA056),
    Color(0xFF4B4B15),
    Color(0xC80B8A27),
    Color(0xFFAD4322),
    Color(0xFFD5D5B2),
    Color(0xFF696960),
    Color(0xFFDDDD8B),
    Color(0xFFE351FC),
    Color(0xFF246D45),
    Color(0xFF3C3C0A),
    Color(0xFF296D40),
    Color(0xFFEE7238),
    Color(0xFFD8917A),
    Color(0xFF2D134D),
    Color(0xFFA17FEB),
    Color(0xFF7D5F91),
    Color(0xFF5C5C2D),
    Color(0xFF163D08),
    Color(0xFF489705),
    Color(0xFF076D04),
    Color(0xFF578D50),
    Color(0xFF444404),
    Color(0xD7CB1ED1),
    Color(0xFF5980C0),
    Color(0xFFFF864D),
    Color(0xFF18465A),
    Color(0xFF818FE0),
    Color(0xFF4D4D04),
    Color(0xFF22686F),
    Color(0xFF9E9E7E),
    Color(0xFF70532D),
    Color(0xFF3AA360),
    Color(0xFF9EFDFD),
    Color(0xFFC4127C),
    Color(0xFF5A4E26),
    Color(0xFF14613C),
).shuffled()
