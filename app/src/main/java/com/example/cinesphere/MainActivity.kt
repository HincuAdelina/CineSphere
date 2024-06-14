package com.example.cinesphere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinesphere.data.movie.MovieAdapter
import com.example.cinesphere.data.movieAPI.movieApiService
import com.example.cinesphere.data.user.UserController
import kotlinx.coroutines.launch
import com.example.cinesphere.data.user.AuthManager

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var userController: UserController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userController = UserController(this)
        if (!AuthManager.isLoggedIn()) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        userController = UserController(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(emptyList())
        recyclerView.adapter = movieAdapter

        lifecycleScope.launch {
            try {
                val movies = movieApiService.getMovies()
                movieAdapter.setData(movies)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error fetching movies", Toast.LENGTH_SHORT).show()
            }
        }

    }
}


