package com.example.cinesphere.data.movie

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieModel>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): MovieModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieModel)

    @Update
    fun updateMovie(movie: MovieModel)

    @Delete
    fun deleteMovie(movie: MovieModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieModel>)
}
