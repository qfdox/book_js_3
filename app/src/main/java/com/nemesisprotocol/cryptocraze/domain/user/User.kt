
package com.nemesisprotocol.cryptocraze.domain.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val username: String,
    @ColumnInfo val password: String
)