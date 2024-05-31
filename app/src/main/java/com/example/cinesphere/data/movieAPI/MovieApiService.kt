package com.example.cinesphere.data.movieAPI

import com.example.cinesphere.data.movie.MovieModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieApiService {
    @GET("movies")
    suspend fun getMovies(): List<MovieModel>
}

val retrofit = Retrofit.Builder()
    .baseUrl("http://www.omdbapi.com/?apikey=[5611179a]&")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val movieApiService = retrofit.create(MovieApiService::class.java)
