package com.example.cinesphere.data.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinesphere.R

class MovieAdapter(private var movies: MutableList<MovieModel>,
                   private val context: Context,
                   private val userId: String,
                   private val removeItem: Boolean = false,
                   private val onItemClick: (MovieModel) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val sharedPreferences = context.getSharedPreferences("${userId}_watched_movies_prefs", Context.MODE_PRIVATE)


    fun setData(newMovies: List<MovieModel>) {
        movies = newMovies.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.titleTextView.text = movie.title

        Glide.with(holder.posterImageView.context)
            .load(movie.poster)
            .into(holder.posterImageView)


        val movieId = movie.id.toString()
        val isWatched = getWatchedStatus(movieId)
        holder.watchedButton.text = if (isWatched) "Watched" else "Add to watched"


        holder.watchedButton.setOnClickListener {
            val currentlyWatched = getWatchedStatus(movieId)
            val newStatus = !currentlyWatched
            updateWatchedStatus(movieId, newStatus)
            holder.watchedButton.text = if (newStatus) "Watched" else "Add to watched"
            if (removeItem){
                movies.filter { it.id == movie.id}.firstOrNull()?.let {movies.remove(it) }
                notifyDataSetChanged()
            }

        }

        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }
    override fun getItemCount(): Int = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.movieTitle)
        val posterImageView: ImageView = itemView.findViewById(R.id.moviePoster)
        val watchedButton: Button = itemView.findViewById(R.id.watchedButton)
    }

    private fun getWatchedStatus(movieId: String): Boolean {
        val prefs = context.getSharedPreferences("${userId}_watched_movies_prefs", Context.MODE_PRIVATE)
        return prefs.getBoolean("${movieId}_watched", false)
    }

    private fun updateWatchedStatus(movieId: String, newStatus: Boolean) {
        val prefs = context.getSharedPreferences("${userId}_watched_movies_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("${movieId}_watched", newStatus).apply()
        val watchedMovies = HashSet(prefs.getStringSet("watched_movies", emptySet()) ?: emptySet())
        if (newStatus) {
            watchedMovies.add(movieId)
        } else {
            watchedMovies.remove(movieId)
        }
        prefs.edit().putStringSet("watched_movies", watchedMovies).apply()
    }
}

