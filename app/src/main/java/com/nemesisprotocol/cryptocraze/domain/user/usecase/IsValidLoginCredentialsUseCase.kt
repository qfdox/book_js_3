
package com.nemesisprotocol.cryptocraze.domain.user.usecase

import com.nemesisprotocol.cryptocraze.domain.user.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsValidLoginCredentialsUseCase @Inject constructor(private val userRepo: UserRepo) {
    suspend operator fun invoke(username: String, password: String) =
        withContext(Dispatchers.Default) {
            userRepo.isValidLoginCredentials(username, password)
        }
}