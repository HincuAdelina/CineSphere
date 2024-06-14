package com.example.cinesphere

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
import com.example.cinesphere.data.movieAPI.movieApiService
import kotlinx.coroutines.launch

class AllMoviesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_movies, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewAllMovies)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter(emptyList())
        recyclerView.adapter = movieAdapter

        lifecycleScope.launch {
            try {
                val movies = movieApiService.getMovies()
                movieAdapter.setData(movies)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error fetching movies", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}