package com.nemesisprotocol.cryptocraze

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.nemesisprotocol.cryptocraze.data.database.user.UserDao
import com.nemesisprotocol.cryptocraze.data.database.user.UserDatabase
import com.nemesisprotocol.cryptocraze.domain.user.User
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class UserDatabaseTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_user_db")
    lateinit var database: UserDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        hiltRule.inject()
        userDao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_addUser() = runBlockingTest {
        val user = User(
            username = "user1",
            password = "password123",
        )
        userDao.addUser(user)
        val allUsers = userDao.getUsers().getOrAwaitValue()
        assertThat(allUsers).contains(user)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_getUserByUsername() = runBlockingTest {
        val user = User(
            username = "user1",
            password = "password123",
        )
        userDao.addUser(user)
        val fetchedUser = userDao.getUserByUsername("user1")
        assertThat(fetchedUser.username).isEqualTo("user1")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_checkUserExists() = runBlockingTest {
        val user1 = User(
            username = "user1",
            password = "password123",
        )
        userDao.addUser(user1)
        val userExists1 = userDao.checkUserExists("user1")
        assertThat(userExists1).isEqualTo(true)
        val userExists2 = userDao.checkUserExists("user2")
        assertThat(userExists2).isEqualTo(false)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_isValidLoginCredentials() = runBlockingTest {
        val user1 = User(
            username = "user1",
            password = "password123",
        )
        userDao.addUser(user1)
        val userExists1 = userDao.checkUserExists("user1")
        assertThat(userExists1).isEqualTo(true)
        val isValidLoginCredentials1 = userDao.isValidLoginCredentials("user1", "password123")
        val isValidLoginCredentials2 = userDao.isValidLoginCredentials("user1", "123")
        val isValidLoginCredentials3 = userDao.isValidLoginCredentials("user2", "password123")
        assertThat(isValidLoginCredentials1).isEqualTo(true)
        assertThat(isValidLoginCredentials2).isEqualTo(false)
        assertThat(isValidLoginCredentials3).isEqualTo(false)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_deleteUser() = runBlockingTest {
        val user = User(
            username = "user1",
            password = "password123",
        )
        userDao.addUser(user)
        var allUsers = userDao.getUsers().getOrAwaitValue()
        assertThat(allUsers).contains(user)
        assertThat(allUsers.size).isEqualTo(1)
        userDao.deleteUser(user)
        allUsers = userDao.getUsers().getO