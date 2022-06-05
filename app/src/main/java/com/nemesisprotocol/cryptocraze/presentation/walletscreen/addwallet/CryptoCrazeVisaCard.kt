
package com.nemesisprotocol.cryptocraze.presentation.walletscreen.addwallet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nemesisprotocol.cryptocraze.R
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.CryptoCrazeVisaColour

@ExperimentalAnimationApi
@Composable
fun CryptoCrazeVisaCard(cardColour: MutableState<CryptoCrazeVisaColour>) {
    val animatedColor = animateColorAsState(
        targetValue =
        when (cardColour.value) {
            CryptoCrazeVisaColour.BLACK -> Color(0xFF000000)
            CryptoCrazeVisaColour.WHITE -> Color(0xFFFFFFFF)
            CryptoCrazeVisaColour.RED -> Color(0xFFB53737)
            CryptoCrazeVisaColour.GREEN -> Color(0xFF26580F)
            CryptoCrazeVisaColour.BLUE -> Color(0xFF003166)
            CryptoCrazeVisaColour.SILVER -> Color(0xFFC0C0C0)
        }
    )
    Box(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(220.dp),
            shape = RoundedCornerShape(25.dp),
            color = animatedColor.value,
            elevation = 18.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val (symbol, logo, number) = createRefs()

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
                        visible = true,
                        modifier = Modifier
                            .padding(20.dp)
                            .constrainAs(logo) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.visa_logo
                            ),
                            contentDescription = "symbol"
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.cryptocraze_icon),
                        contentDescription = null,
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
                }
            }
        }
    }
}