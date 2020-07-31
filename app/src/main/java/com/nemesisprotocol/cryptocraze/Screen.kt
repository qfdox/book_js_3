package com.nemesisprotocol.cryptocraze

sealed class Screen(val route: String, val icon: Int?, val title: String) {
    object Splash : Screen("splash_screen", null, "Splash")
    obj