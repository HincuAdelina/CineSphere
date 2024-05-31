package com.example.cinesphere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinesphere.data.movie.MovieAdapter
import com.example.cinesphere.data.movieAPI.movieApiService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(emptyList())
        recyclerView.adapter = movieAdapter

        lifecycleScope.launch {
            try {
                val movies = movieApiService.getMovies()
                movieAdapter.setData(movies)
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching movies", e)
                Toast.makeText(this@MainActivity, "Error fetching movies", Toast.LENGTH_SHORT).show()
            }
        }

    }
}


