package com.example.cinesphere.data.user

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserModel>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): UserModel?

    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): UserModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserModel)

    @Update
    fun updateUser(user: UserModel)

    @Delete
    fun deleteUser(user: UserModel)

}