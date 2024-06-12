package com.example.cinesphere.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val email: String,
    val password: String
)