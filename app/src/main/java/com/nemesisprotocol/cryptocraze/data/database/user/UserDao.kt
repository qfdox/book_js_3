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
    fun g