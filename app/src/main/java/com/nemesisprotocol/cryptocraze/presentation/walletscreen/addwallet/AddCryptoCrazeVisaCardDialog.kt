
package com.nemesisprotocol.cryptocraze.presentation.walletscreen.addwallet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.WalletViewModel

@Composable
fun AddCryptoCrazeVisaCardDialog(
    addCryptoCrazeVisaCardDialog: MutableState<Boolean>,
    navController: NavHostController
) {
    val walletViewModel: WalletViewModel = hiltViewModel()
    val cryptoCrazeVisaCards = walletViewModel.cryptoCrazeVisaCards.collectAsState()

    Column {

        if (addCryptoCrazeVisaCardDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    addCryptoCrazeVisaCardDialog.value = false
                },
                title = {
                    Text(text = "Your Crypto Craze Visa Cards")
                },
                text = {
                    if (cryptoCrazeVisaCards.value.isNotEmpty()) {
                        LazyColumn {
                            items(cryptoCrazeVisaCards.value) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp)
                                        .clickable {
                                            navController.navigate(
                                                Screen.AddCryptoCrazeVisaCard.route +
                                                    "/${Gson().toJson(it)}"
                                            )
                                        }
                                ) {
                                    Text(
                                        "Crypto Craze ${it.cryptoCrazeVisaColour.name} Visa Card",
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }
                    } else {
                        Text("You do not have a Crypto Craze Visa Card. \nSelect Create to add a Crypto Craze Visa Card")
                    }
                },
                confirmButton = {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            addCryptoCrazeVisaCardDialog.value = false
                            navController.navigate(
                                Screen.AddCryptoCrazeVisaCard.route + "/ "
                            )
                        }
                    ) {
                        Text("Create")
                    }
                },
                dismissButton = {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            addCryptoCrazeVisaCardDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }, shape = RoundedCornerShape(30.dp)
                )
            }
        }
    }
    