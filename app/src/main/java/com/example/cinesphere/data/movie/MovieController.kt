package com.example.cinesphere.data.movie

import android.content.Context
import androidx.room.Room
import com.example.cinesphere.data.AppDatabase

class MovieController(context: Context) {
    private val repository: MovieRepository

    init {
        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "movie-database"
        ).build()
        repository = MovieRepository(db.movieDao)
    }

    fun getAllMovies(): List<MovieModel> = repository.getAllMovies()

    fun addMovie(movie: MovieModel) {
        repository.insertMovie(movie)
    }

    fun updateMovie(movie: MovieModel) {
        repository.updateMovie(movie)
    }

    fun deleteMovie(movie: MovieModel) {
        repository.deleteMovie(movie)
    }

    fun getMovieById(id: Int): MovieModel? {
        return repository.getMovieById(id)
    }
}
