package com.nemesisprotocol.cryptocraze.domain.user.usecase

import androidx.lifecycle.LiveData
import com.nemesisprotocol.cryptocraze.domain.user.User
import com.nemesisprotocol.cryptocraze.domain.user.UserRepo
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepo: UserRepo) {
    operator fun invoke(): LiveData<List<User>> = userRepo.getUsers()
}
