
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

package com.nemesisprotocol.cryptocraze.presentation.walletscreen.addwallet

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nemesisprotocol.cryptocraze.R

@ExperimentalAnimationApi
@Composable
fun PaymentCard(
    nameText: TextFieldValue,
    cardNumber: TextFieldValue,
    expiryNumber: TextFieldValue,
    cvvNumber: TextFieldValue
) {
    var backVisible by remember { mutableStateOf(false) }
    var visaType by remember { mutableStateOf(CardType.None) }
    val length = if (cardNumber.text.length > 16) 16 else cardNumber.text.length
    val initial = remember { "*****************" }
        .replaceRange(0..length, cardNumber.text.take(16))

    if (cvvNumber.text.length == 1 && !backVisible) {
        backVisible = true
    } else if (cvvNumber.text.length == 2) {
        backVisible = true
    } else if (cvvNumber.text.length == 3) {
        backVisible = false
    }

    // handle card type logic.
    visaType = if (cardNumber.text.length >= 8) {
        CardType.Visa
    } else {
        CardType.None
    }

    val animatedColor = animateColorAsState(
        targetValue =
        if (visaType == CardType.Visa) {
            Color(0xFF1C478B)
        } else {
            Color(0xFF424242)
        }
    )
    Box(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(220.dp)
                .graphicsLayer(
                    rotationY = animateFloatAsState(if (backVisible) 180f else 0f).value,
                    translationY = 0f
                ),
            shape = RoundedCornerShape(25.dp),
            color = animatedColor.value,
            elevation = 18.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AnimatedVisibility(visible = !backVisible) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val (symbol, logo, cardName, cardNameLabel, number, expiry, expiryLabel) = createRefs()

                        Image(
                            painter = painterResource(
                                id = R.drawable.card_symbol
                            ),
                            contentDescription = "symbol",
                            modifier = Modifier
                                .padding(20.dp)
                                .constrainAs(symbol) {
                                    start.linkTo(parent.start)
                                    top.linkTo(parent.top)
                                }
                        )

                        AnimatedVisibility(
                            visible = visaType != CardType.None,
                            modifier = Modifier
                                .padding(20.dp)
                                .constrainAs(logo) {
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                }
                        ) {
                            Image(
                                painter = painterResource(
                                    id = visaType.image
                                ),
                                contentDescription = "symbol"
                            )
                        }

                        Text(
                            text = initial.chunked(4).joinToString(" "),
                            style = MaterialTheme.typography.h5,
                            maxLines = 1,
                            color = Color.White,
                            modifier = Modifier
                                .animateContentSize(spring())
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .constrainAs(number) {
                                    linkTo(
                                        start = parent.start,
                                        end = parent.end
                                    )
                                    linkTo(
                                        top = parent.top,
                                        bottom = parent.bottom
                                    )
                                }
                        )

                        Text(
                            text = "CARD HOLDER",
                            style = MaterialTheme.typography.caption,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .constrainAs(cardNameLabel) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(cardName.top)
                                }
                        )
                        Text(
                            text = nameText.text,
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            modifier = Modifier
                                .animateContentSize(TweenSpec(300))
                                .padding(start = 16.dp, bottom = 16.dp)
                                .constrainAs(cardName) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                }
                        )

                        Text(
                            text = "Expiry",
                            style = MaterialTheme.typography.caption,
                            color = Color.White,
                            modifier = Modifier