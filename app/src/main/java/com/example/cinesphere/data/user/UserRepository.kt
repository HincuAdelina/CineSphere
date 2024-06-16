package com.example.cinesphere.data.user

class UserRepository(private val userDao: UserDao) {

    fun getAllUsers(): List<UserModel> {
        return userDao.getAllUsers()
    }

    fun getUserById(id: Int): UserModel? {
        return userDao.getUserById(id)
    }

    fun getUserByEmail(email: String): UserModel? {
        return userDao.getUserByEmail(email)
    }

    fun insertUser(user: UserModel) {
        userDao.insertUser(user)
    }

    fun updateUser(user: UserModel) {
        userDao.updateUser(user)
    }

    fun deleteUser(user: UserModel) {
        userDao.deleteUser(user)
    }

    suspend fun loginUser(email: String, password: String): UserModel? {
        return userDao.getUserByEmail(email)?.takeIf { it.password == password }
    }

}