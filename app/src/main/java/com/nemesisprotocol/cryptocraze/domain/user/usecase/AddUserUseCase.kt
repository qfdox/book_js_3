package com.nemesisprotocol.cryptocraze.domain.user.usecase

import com.nemesisprotocol.cryptocraze.domain.user.User
import com.nemesisprotocol.cryptocraze.domain.user.UserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepo: UserRepo) {
    private val addUserCoroutineScope = CoroutineScope(Dispatchers.Default)
    operator fun invoke(user: User) = addUserCoroutineScope.launch { userRepo.addUser(user) }
}
