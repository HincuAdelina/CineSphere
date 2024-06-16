package com.example.cinesphere.data.user

import android.content.Context
import androidx.room.Room
import com.example.cinesphere.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserController(context: Context) {
    private val repository: UserRepository



    init {
        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "user-database"
        ).build()
        repository = UserRepository(db.userDao)
    }

    suspend fun getAllUsers(): List<UserModel> = withContext(Dispatchers.IO) {
        repository.getAllUsers()
    }

    suspend fun updateUser(user: UserModel) {
        withContext(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    suspend fun deleteUser(user: UserModel) {
        withContext(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    suspend fun getUserById(id: Int): UserModel? {
        return withContext(Dispatchers.IO) {
            repository.getUserById(id)
        }
    }


    suspend fun loginUser(username: String, password: String): UserModel? {
        return withContext(Dispatchers.IO) {
            repository.loginUser(username, password)
        }
    }

    suspend fun registerUser(user: UserModel) {
        withContext(Dispatchers.IO) {
            val existingUser = repository.getUserByEmail(user.email)

            if (existingUser != null) {
                throw IllegalArgumentException("User with this email already exists")
            }

            repository.insertUser(user)
        }
    }
}