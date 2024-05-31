package com.example.cinesphere.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinesphere.data.movie.MovieDao
import com.example.cinesphere.data.movie.MovieModel

@Database(
    entities = [
        MovieModel::class
               ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao


}
