package com.nemesisprotocol.cryptocraze.presentation.login

import androidx.lifecycle.ViewModel
import com.nemesisprotocol.cryptocraze.common.DispatcherProvider
import com.nemesisprotocol.cryptocraze.domain.user.User
import com.nemesisprotocol.cryptocraze.domain.user.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val checkUserExistsUseCase: CheckUserExistsUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val isValidLoginCredentialsUseCase: IsValidLoginCredentialsUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    fun createUser(username: String, password: String, confirmPassword: String) {
        if (password == confirmPassword) addUserUseCase(
            User(
                username = username,
                password = password
            )
        )
    }

    suspend fun login(username: String, password: String): Boolean {
        return withContext(dispatcherProvider.io) {
            val isLoginValid = isValidLoginCredentialsUseCase(username, password)
            isLoginValid
        }
    }

    suspend fun checkUserExists(username: String): Boolean {
        return withContext(dispatcherProvider.io) {
            val userExists = checkUserExistsUseCase(username)
            userExists
        }
    }
}
