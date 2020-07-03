package com.nemesisprotocol.cryptocraze

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.nemesisprotocol.cryptocraze.data.database.user.UserDao
import com.nemesisprotocol.cryptocraze.data.database.user.UserDatabase
import com.nemesisprotocol.cryptocraze.domain.user.User
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
imp