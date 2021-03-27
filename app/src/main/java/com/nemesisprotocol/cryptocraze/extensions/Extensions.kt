package com.nemesisprotocol.cryptocraze.extensions

import java.text.DecimalFormat

private val twoDecimalFormat = DecimalFormat("##.##")
private val threeDecimalFormat = DecimalFormat("##.###")
fun Double.roundToTwoDecimals() = twoDecimalFormat.format(this).toString()
fun Double.roundToThreeDecimals() = threeDecimalFor