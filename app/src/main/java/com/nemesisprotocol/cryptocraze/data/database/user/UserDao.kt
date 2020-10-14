package com.nemesisprotocol.cryptocraze.data.database.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nemesisprotocol.cryptocraze.domain.user.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * from user where username = :username")
    fun getUserByUsername(username: String): User

    @Query("SELECT EXISTS(SELECT * FROM user WHERE username = :username)")
    fun checkUserExists(username: String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM user WHERE username = :username AND password = :password)")
    fun isValidLoginCredentials(username: String, password: String): Boolean

    @Insert
    fun addUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user")
    fun wipeUsers()
}
