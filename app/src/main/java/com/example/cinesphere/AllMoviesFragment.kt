package com.example.cinesphere

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinesphere.data.movie.MovieAdapter
import com.example.cinesphere.data.movie.MovieModel
import com.example.cinesphere.data.movieAPI.movieApiService
import kotlinx.coroutines.launch

class AllMoviesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_movies, container, false)

        userId = getUserIdFromSharedPreferences(requireContext())
        sharedPreferences = requireContext().getSharedPreferences("${getUserIdFromSharedPreferences(requireContext())}_watched_movies_prefs", Context.MODE_PRIVATE)


        recyclerView = view.findViewById(R.id.recyclerViewAllMovies)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter(mutableListOf(), requireContext(), userId)
        recyclerView.adapter = movieAdapter

        fetchMovies()

        return view
    }

    private fun fetchMovies() {
        lifecycleScope.launch {
            try {
                val movies = movieApiService.getMovies()
                movieAdapter.setData(movies)
                updateWatchedMoviesStatus(movies)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error fetching movies", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun updateWatchedMoviesStatus(movies: List<MovieModel>) {
        val watchedMovies = getWatchedMovies()
        movies.forEach { movie ->
            movie.watched = watchedMovies.contains(movie.id.toString())
        }
        movieAdapter.setData(movies)
    }

    private fun getUserIdFromSharedPreferences(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userId", "") ?: ""
    }

    private fun getWatchedMovies(): Set<String> {
        val prefs = requireContext().getSharedPreferences("${userId}_watched_movies_prefs", Context.MODE_PRIVATE)
        return prefs.getStringSet("watched_movies", emptySet()) ?: emptySet()
    }
}
