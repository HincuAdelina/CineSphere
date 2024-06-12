package com.example.cinesphere.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinesphere.data.movie.MovieDao
import com.example.cinesphere.data.movie.MovieModel
import com.example.cinesphere.data.user.UserDao
import com.example.cinesphere.data.user.UserModel

@Database(
    entities = [
        MovieModel::class,
        UserModel::class
               ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val userDao: UserDao

}
