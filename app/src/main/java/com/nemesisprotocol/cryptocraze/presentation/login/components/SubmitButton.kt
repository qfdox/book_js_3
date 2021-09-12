package com.nemesisprotocol.cryptocraze.presentation.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SubmitButton(
    textId: Int,
    validInputs: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .pad