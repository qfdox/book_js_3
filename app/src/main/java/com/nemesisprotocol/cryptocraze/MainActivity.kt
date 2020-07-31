
package com.nemesisprotocol.cryptocraze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.google.gson.Gson
import com.nemesisprotocol.cryptocraze.presentation.BottomNavigationBar
import com.nemesisprotocol.cryptocraze.presentation.BottomSheetContent
import com.nemesisprotocol.cryptocraze.presentation.Navigation
import com.nemesisprotocol.cryptocraze.presentation.PortfolioViewModel
import com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen.TransactionType
import com.nemesisprotocol.cryptocraze.presentation.homescreen.HomeViewModel
import com.nemesisprotocol.cryptocraze.presentation.ui.theme.CryptoCrazeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @ExperimentalCoilApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCrazeTheme {

                val homeViewModel: HomeViewModel = hiltViewModel()
                val portfolioViewModel: PortfolioViewModel = hiltViewModel()
                val pagingCryptoDataItems = homeViewModel.getAllCryptos().collectAsLazyPagingItems()
                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()

                val currentRoute = remember {
                    mutableStateOf(
                        navBackStackEntry?.destination?.route ?: Screen.Splash.route
                    )
                }

                val userLoggedIn = remember { mutableStateOf(false) }

                val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
                )
                val bottomSheetContent = remember { mutableStateOf(BottomSheetContent.BUY_SELL) }
                val listScrollState = rememberLazyListState()

                val coroutineScope = rememberCoroutineScope()

                BottomSheetScaffold(
                    scaffoldState = bottomSheetScaffoldState,
                    sheetContent = {
                        if (bottomSheetScaffoldState.bottomSheetState.currentValue == BottomSheetValue.Collapsed) {
                            bottomSheetContent.value = BottomSheetContent.BUY_SELL
                        }
                        if (bottomSheetContent.value == BottomSheetContent.BUY_SELL) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Row {
                                        Button(
                                            modifier = Modifier.padding(16.dp),
                                            onClick = {
                                                bottomSheetContent.value = BottomSheetContent.BUY
                                            }
                                        ) {
                                            Text("Buy")
                                        }
                                        Button(
                                            modifier = Modifier.padding(16.dp),
                                            onClick = {
                                                bottomSheetContent.value = BottomSheetContent.SELL
                                            }
                                        ) {
                                            Text("Sell")
                                        }
                                    }
                                }
                            }
                        }
                        if (bottomSheetContent.value == BottomSheetContent.BUY) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(top = 8.dp, start = 8.dp)
                                        .size(32.dp)
                                        .clickable {
                                            bottomSheetContent.value =
                                                BottomSheetContent.BUY_SELL
                                        },
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "ArrowBack",
                                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Text(
                                        modifier = Modifier.padding(16.dp),
                                        text = "Buy Cryptocurrency",
                                        fontSize = 24.sp,
                                        textAlign = TextAlign.Center
                                    )

                                    LazyColumn(state = listScrollState) {
                                        itemsIndexed(pagingCryptoDataItems) { _, crypto ->
                                            crypto?.let {
                                                Card(
                                                    backgroundColor = MaterialTheme.colors.surface.copy(
                                                        alpha = 0.5f
                                                    ),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .fillMaxHeight()
                                                        .height(64.dp)
                                                        .padding(
                                                            start = 8.dp,
                                                            end = 8.dp,
                                                            top = 16.dp
                                                        )
                                                        .clickable {
                                                            coroutineScope.launch {
                                                                bottomSheetScaffoldState.bottomSheetState.collapse()
                                                                navController.navigate(
                                                                    Screen.CryptoTransaction.route + "/${
                                                                    Gson().toJson(
                                                                        it.mapPriceInfo()
                                                                    )
                                                                    }/${TransactionType.BUY.name}"
                                                                )
                                                            }
                                                        }
                                                ) {
                                                    Column(
                                                        modifier = Modifier.fillMaxSize(),
                                                        verticalArrangement = Arrangement.Center,
                                                    ) {
                                                        Text(
                                                            modifier = Modifier.padding(start = 8.dp),
                                                            text = "(${it.symbol.uppercase()}) ${it.name}",
                                                            fontSize = 18.sp
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                        pagingCryptoDataItems.apply {
                                            when {
                                                loadState.refresh is LoadState.Loading -> item {
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .fillMaxHeight()
                                                            .padding(top = 32.dp),
                                                        horizontalAlignment = Alignment.CenterHorizontally,
                                                        verticalArrangement = Arrangement.Center
                                                    ) {
                                                        CircularProgressIndicator()
                                                        Text(text = "Loading Crypto Data...")
                                                    }
                                                }
                                                loadState.append is LoadState.Loading -> {
                                                    item {
                                                        Column(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .fillMaxHeight()
                                                                .padding(top = 32.dp),
                                                            horizontalAlignment = Alignment.CenterHorizontally,
                                                            verticalArrangement = Arrangement.Center
                                                        ) {
                                                            CircularProgressIndicator()
                                                            Text(text = "Loading Crypto Data...")
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (bottomSheetContent.value == BottomSheetContent.SELL) {

                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(top = 8.dp, start = 8.dp)
                                        .size(32.dp)
                                        .clickable {
                                            bottomSheetContent.value =
                                                BottomSheetContent.BUY_SELL
                                        },
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "ArrowBack",
                                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Text(
                                        modifier = Modifier.padding(16.dp),
                                        text = "Sell Cryptocurrency",
                                        fontSize = 24.sp,
                                        textAlign = TextAlign.Center
                                    )

                                    LazyColumn(state = listScrollState) {
                                        itemsIndexed(pagingCryptoDataItems) { _, crypto ->
                                            crypto?.let {
                                                var cryptoIsInvested by remember { mutableStateOf(false) }
                                                LaunchedEffect(Unit) {
                                                    cryptoIsInvested = portfolioViewModel.checkCryptoIsInvested(crypto.symbol.uppercase())
                                                }
                                                Card(
                                                    backgroundColor = if (cryptoIsInvested) MaterialTheme.colors.surface.copy(
                                                        alpha = 0.5f
                                                    ) else MaterialTheme.colors.surface.copy(
                                                        alpha = 1.0f
                                                    ),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .fillMaxHeight()
                                                        .height(64.dp)
                                                        .padding(
                                                            start = 8.dp,
                                                            end = 8.dp,
                                                            top = 16.dp
                                                        )
                                                        .clickable(cryptoIsInvested) {
                                                            coroutineScope.launch {
                                                                bottomSheetScaffoldState.bottomSheetState.collapse()
                                                                navController.navigate(
                                                                    Screen.CryptoTransaction.route + "/${
                                                                    Gson().toJson(
                                                                        it.mapPriceInfo()
                                                                    )
                                                                    }/${TransactionType.SELL.name}"
                                                                )
                                                            }
                                                        }
                                                ) {
                                                    Column(
                                                        modifier = Modifier.fillMaxSize(),
                                                        verticalArrangement = Arrangement.Center,
                                                    ) {
                                                        Text(
                                                            modifier = Modifier.padding(start = 8.dp),
                                                            color = if (cryptoIsInvested) Color.Unspecified else Color.Gray,
                                                            text = "(${it.symbol.uppercase()}) ${it.name}",
                                                            fontSize = 18.sp
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                        pagingCryptoDataItems.apply {
                                            when {
                                                loadState.refresh is LoadState.Loading -> item {
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .fillMaxHeight()
                                                            .padding(top = 32.dp),
                                                        horizontalAlignment = Alignment.CenterHorizontally,
                                                        verticalArrangement = Arrangement.Center
                                                    ) {
                                                        CircularProgressIndicator()
                                                        Text(text = "Loading Crypto Data...")
                                                    }
                                                }
                                                loadState.append is LoadState.Loading -> {
                                                    item {
                                                        Column(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .fillMaxHeight()
                                                                .padding(top = 32.dp),
                                                            horizontalAlignment = Alignment.CenterHorizontally,
                                                            verticalArrangement = Arrangement.Center
                                                        ) {
                                                            CircularProgressIndicator()
                                                            Text(text = "Loading Crypto Data...")
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }, sheetPeekHeight = 0.dp
                ) {
                    Scaffold(
                        topBar = {
                            if (userLoggedIn.value) {
                                TopAppBar(
                                    title = {
                                    },
                                    actions = {
                                        if (currentRoute.value == Screen.Wallet.route) {
                                            IconButton(onClick = {
                                                navController.navigate(Screen.TransactionHistory.route)
                                            }) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.transaction_history_24),
                                                    ""
                                                )
                                            }
                                        }
                                        IconButton(onClick = {
                                            navController.navigate(Screen.Settings.route)
                                        }) {
                                            Icon(Icons.Rounded.Settings, "")
                                        }
                                    }
                                )
                            }
                        },
                        bottomBar = {
                            if (userLoggedIn.value) {
                                BottomNavigationBar(navController, bottomSheetScaffoldState)
                            }
                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            Navigation(
                                navController,
                                userLoggedIn,
                                homeViewModel,
                                currentRoute
                            )
                        }
                    }
                }
            }
        }
    }
}