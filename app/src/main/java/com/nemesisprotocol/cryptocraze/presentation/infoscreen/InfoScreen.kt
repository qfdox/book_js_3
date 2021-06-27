package com.nemesisprotocol.cryptocraze.presentation.infoscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.nemesisprotocol.cryptocraze.presentation.infoscreen.coininfolist.CoinInfoListScreen

@Composable
fun InfoScreen(navController: NavController) {
    CoinInfoListScreen(navController = navController)
}
