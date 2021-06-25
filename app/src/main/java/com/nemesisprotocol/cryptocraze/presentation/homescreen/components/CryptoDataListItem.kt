
/*
* MIT License
*
* Copyright (c) 2020 Gurupreet Singh
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
 */

package com.nemesisprotocol.cryptocraze.presentation.homescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoData
import com.nemesisprotocol.cryptocraze.extensions.roundToThreeDecimals
import com.nemesisprotocol.cryptocraze.extensions.roundToTwoDecimals
import com.nemesisprotocol.cryptocraze.presentation.LineChart
import com.nemesisprotocol.cryptocraze.presentation.homescreen.HomeViewModel
import com.nemesisprotocol.cryptocraze.presentation.ui.theme.gradientGreenColors
import com.nemesisprotocol.cryptocraze.presentation.ui.theme.gradientRedColors

@ExperimentalCoilApi
@Composable
fun CryptoDataListItem(
    cryptoData: CryptoData,
    isFav: MutableState<Boolean>,
    viewModel: HomeViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = cryptoData.image),
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.weight(0.4f)) {
            Text(
                text = cryptoData.symbol.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "£${cryptoData.price}",
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            LineChart(
                modifier = Modifier
                    .width(200.dp)
                    .height(70.dp)
                    .align(Alignment.CenterHorizontally),
                yAxisValues = cryptoData.chartData,
                lineColors = if (cryptoData.dailyChange > 0) gradientGreenColors else gradientRedColors
            )
            Text(
                text = cryptoData.dailyChange.roundToThreeDecimals() +
                    " (${cryptoData.dailyChangePercentage.roundToTwoDecimals()}%)",
                color = if (cryptoData.dailyChange > 0) Color.Green else Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End)
            )
        }
        IconToggleButton(
            checked = isFav.value,
            onCheckedChange = {
                if (!isFav.value) {
                    viewModel.addFavCrypto(cryptoData)
                    isFav.value = !isFav.value
                } else {
                    viewModel.deleteFavCrypto(cryptoData)
                    isFav.value = !isFav.value
                }
            }
        ) {
            Icon(
                imageVector = if (isFav.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = if (isFav.value) Color.Red else MaterialTheme.colors.onSurface
            )
        }
    }
}