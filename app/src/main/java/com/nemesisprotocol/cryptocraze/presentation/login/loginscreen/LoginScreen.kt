package com.nemesisprotocol.cryptocraze.presentation.login.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nemesisprotocol.cryptocraze.R
import com.nemesisprotocol.cryptocraze.Screen
import com.nemesisprotocol.cryptocraze.presentation.login.LoginViewModel
import com.nemesisprotocol.cryptocraze.presentation.login.components.PasswordTextField
import com.nemesisprotocol.cryptocraze.presentation.login.components.SubmitButton
import com.nemesisprotocol.cryptocraze.presentation.login.components.UsernameTextField
import kotlinx.coroutines.*

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    userLoggedIn: MutableState<Boolean>,
    navController: NavHostController,
    model: LoginViewModel = hiltViewModel()
) {

    val coroutineScope = rememberCoroutineScope()
    SignIn(userLoggedIn, navController) { email: String, password: String ->
        coroutineScope.async {
            model.login(email, password)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun SignIn(
    userLoggedIn: MutableState<Boolean>,
    navController: NavHostController,
    onDone: (String, String) -> Deferred<Boolean>
) {
    val username = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequester = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValid = remember(username.value, password.value) {
        username.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())

    val coroutineScope = rememberCoroutineScope()

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.app_name_title),
            modifier = Modifier.padding(vertical = 50.dp, horizontal = 15.dp),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
        UsernameTextField(
            valueState = username,
            onAction = KeyboardActions {
                passwordFocusRequester.requestFocus()
            }
        )
        PasswordTextField(
            modifier = Modifier.focusRequester(passwordFocusRequester),
            passwordState = password,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!isValid) return@KeyboardActions
                onDone(username.value.trim(), password.value.trim())
                keyboardController?.hide()
            }
        )
        SubmitButton(
            textId = R.string.sign_in,
            validInputs = isValid
        ) {
            coroutineScope.launch {
                if (onDone(
                        username.value.trim(),
                        password.value.trim()
                    ).await()
                ) {
                    userLoggedIn.value = true
                    navController.navigate(Screen.Home.route)
                }
            }
            keyboardController?.hide()
        }
        Text(
            text 