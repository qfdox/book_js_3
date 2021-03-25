package com.nemesisprotocol.cryptocraze.domain.user.usecase

import com.nemesisprotocol.cryptocraze.domain.user.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckUserExistsUseCase @Inject constructor(private val userRepo: UserRepo) {
    suspend