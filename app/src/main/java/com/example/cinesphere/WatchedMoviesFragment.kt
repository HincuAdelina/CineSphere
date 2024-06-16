package com.example.cinesphere

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinesphere.data.movie.MovieAdapter
import com.example.cinesphere.data.movie.MovieModel
import com.example.cinesphere.data.movieAPI.movieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WatchedMoviesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_watched_movies, container, false)

        userId = getUserIdFromSharedPreferences(requireContext())
        sharedPreferences = requireContext().getSharedPreferences("${getUserIdFromSharedPreferences(requireContext())}_watched_movies_prefs", Context.MODE_PRIVATE)


        recyclerView = view.findViewById(R.id.recyclerViewWatchedMovies)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter(mutableListOf(), requireContext(), userId, true, onItemClick = { movie ->
            openMovieDetailFragment(movie)
        })
        recyclerView.adapter = movieAdapter

        fetchWatchedMovies()

        return view
    }

    private fun fetchWatchedMovies() {
        GlobalScope.launch(Dispatchers.Main) {
            val allMovies = fetchAllMovies()
            val watchedMovies = getWatchedMovies()
            val watchedMoviesList = allMovies.filter { watchedMovies.contains(it.id.toString()) }
            movieAdapter.setData(watchedMoviesList)
        }
    }

    private suspend fun fetchAllMovies(): List<MovieModel> {
        return try {
            movieApiService.getMovies()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error fetching movies", Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }

    private fun getWatchedMovies(): Set<String> {
        val prefs = requireContext().getSharedPreferences("${userId}_watched_movies_prefs", Context.MODE_PRIVATE)
        return prefs.getStringSet("watched_movies", emptySet()) ?: emptySet()
    }

    private fun getUserIdFromSharedPreferences(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userId", "") ?: ""
    }

    private fun openMovieDetailFragment(movie: MovieModel) {
        val fragment = MovieDetailFragment.newInstance(movie)
        parentFragmentManager.beginTransaction()
            .replace(R.id.movieDetailFragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}
