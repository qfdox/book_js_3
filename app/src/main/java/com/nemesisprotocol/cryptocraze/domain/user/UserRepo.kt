
package com.nemesisprotocol.cryptocraze.domain.user

import androidx.lifecycle.LiveData

interface UserRepo {

    fun getUsers(): LiveData<List<User>>

    fun getUserByUsername(username: String): User

    fun checkUserExists(username: String): Boolean

    fun isValidLoginCredentials(username: String, password: String): Boolean

    fun addUser(user: User)

    fun deleteUser(user: User)

    fun wipeUsers()
}