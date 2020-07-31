package com.nemesisprotocol.cryptocraze

sealed class Screen(val route: String, val icon: Int?, val title: String) {
    object Splash : Screen("splash_screen", null, "Splash")
    object Login : Screen("login_screen", null, "Login")
    object SignUp : Screen("sign_up_screen", null, "Signup")
    object Home : Screen("home_screen", R.drawable.ic_home_24, "Home")
 