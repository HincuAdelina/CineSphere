package com.example.cinesphere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AllMoviesFragment())
                .addToBackStack("AllMoviesFragment")
                .commit()
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_all_movies -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AllMoviesFragment())
                        .addToBackStack("AllMoviesFragment")
                        .commit()
                    true
                }
                R.id.navigation_watched_movies -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, WatchedMoviesFragment())
                        .addToBackStack("WatchedMoviesFragment")
                        .commit()
                    true
                }
                R.id.navigation_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .addToBackStack("ProfileFragment")
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}



