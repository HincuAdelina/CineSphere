package com.example.cinesphere.data.user

class UserRepository(private val userDao: UserDao) {

    suspend fun getAllUsers(): List<UserModel> {
        return userDao.getAllUsers()
    }

    suspend fun getUserById(id: Int): UserModel? {
        return userDao.getUserById(id)
    }

    suspend fun getUserByEmail(email: String): UserModel? {
        return userDao.getUserByEmail(email)
    }

    suspend fun insertUser(user: UserModel) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: UserModel) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: UserModel) {
        userDao.deleteUser(user)
    }

    suspend fun loginUser(email: String, password: String): UserModel? {
        return userDao.getUserByEmail(email)?.takeIf { it.password == password }
    }
}