package com.nemesisprotocol.cryptocraze.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nemesisprotocol.cryptocraze.Screen
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    val items = listOf(
        Screen.Home,
        Screen.Wallet,
        Screen.CryptoCrazeLogo,
        Screen.Portfolio,
        Screen.Info
    )
    BottomNavigation(
        contentColor = Color.White
    ) {
        val coroutineScope = rememberCoroutineScope()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            if (item != Screen.CryptoCrazeLogo) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon!!),
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(text = item.title)
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                    alwaysShowLabel = true,
                    selected = currentRoute == item.route,
       