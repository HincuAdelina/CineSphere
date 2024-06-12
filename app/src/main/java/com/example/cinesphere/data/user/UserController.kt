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

    fun getAllUsers(): List<UserModel> = repository.getAllUsers()

    fun updateUser(user: UserModel) {
        repository.updateUser(user)
    }

    fun deleteUser(user: UserModel) {
        repository.deleteUser(user)
    }

    fun getUserById(id: Int): UserModel? {
        return repository.getUserById(id)
    }

    fun loginUser(username: String, password: String): UserModel? {
        return repository.getAllUsers().find { it.username == username && it.password == password }
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