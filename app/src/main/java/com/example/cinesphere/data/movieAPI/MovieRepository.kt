package com.example.cinesphere.data.movieAPI

import com.example.cinesphere.data.movie.MovieDao
import com.example.cinesphere.data.movie.MovieModel

class MovieRepository(private val movieDao: MovieDao, private val movieApiService: MovieApiService) {

    suspend fun getMovies(): List<MovieModel> {
        val movieApiResponse = movieApiService.getMovies()

        val movies = movieApiResponse.map {
            MovieModel(title = it.title, plot = it.plot, poster = it.poster, watched = false, imdbID = it.imdbID)
        }

        movieDao.insertAll(movies)

        return movieDao.getAllMovies()
    }
}
