package com.example.cinesphere.data.movie

class MovieRepository(private val movieDao: MovieDao) {

    fun getAllMovies(): List<MovieModel> {
        return movieDao.getAllMovies()
    }

    fun getMovieById(id: Int): MovieModel? {
        return movieDao.getMovieById(id)
    }

    fun insertMovie(movie: MovieModel) {
        movieDao.insertMovie(movie)
    }

    fun updateMovie(movie: MovieModel) {
        movieDao.updateMovie(movie)
    }

    fun deleteMovie(movie: MovieModel) {
        movieDao.deleteMovie(movie)
    }
}
