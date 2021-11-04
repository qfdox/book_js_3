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

    val hashMa