/*
* Copyright 2020 Taras Koshkin.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */

package com.nemesisprotocol.cryptocraze.presentation.portfolioscreen.piechart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.nemesisprotocol.cryptocraze.presentation.portfolioscreen.piechart.PieChartUtils.calculateAngle
import com.nemesisprotocol.cryptocraze.presentation.portfolioscreen.piechart.renderer.SimpleSliceDrawer
import com.nemesisprotocol.cryptocraze.presentation.portfolioscreen.piechart.renderer.SliceDrawer

@Composable
fun PieChart(
    pieChartData: PieChartData,
    modifier: Modifier = Modifier,
    animation: AnimationSpec<Float> = 