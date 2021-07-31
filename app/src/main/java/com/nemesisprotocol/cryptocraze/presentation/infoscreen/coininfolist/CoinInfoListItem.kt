package com.nemesisprotocol.cryptocraze.presentation.infoscreen.coininfolist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nemesisprotocol.cryptocraze.R
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.Coin

@Composable
fun CoinInfoListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(coin) }
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${coin.name} (${coin.symbol})",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp
            )
            if (coin.isActive) {
                Image(
                    modifier = Modifier
                        .align(CenterVertically),
                    painter = painterResource(id = R.drawable.crypto_coin_is_active_icon),
                    contentDescription = "Crypto Coin Active"
                )
            } else {
                Image(
                    modifier = Modifier
                        .align(CenterVertically),
                    painter = painterResource(id = R.drawable.crypto_coin_inactive_icon),
                    contentDescription = "Crypto Coin Inactive"
                )
            }
        }
    }
}
