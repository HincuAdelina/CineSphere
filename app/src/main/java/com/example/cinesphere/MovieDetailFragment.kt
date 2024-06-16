package com.example.cinesphere

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cinesphere.data.movie.MovieModel

class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        val titleTextView: TextView = view.findViewById(R.id.movieTitle)
        val posterImageView: ImageView = view.findViewById(R.id.moviePoster)
        val plotTextView: TextView = view.findViewById(R.id.moviePlot)

        arguments?.let {
            val title = it.getString("title")
            val poster = it.getString("poster")
            val plot = it.getString("plot")

            titleTextView.text = title
            plotTextView.text = plot
            Glide.with(this).load(poster).into(posterImageView)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(movie: MovieModel) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("title", movie.title)
                    putString("poster", movie.poster)
                    putString("plot", movie.plot)
                }
            }
    }
}