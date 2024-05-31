package com.example.cinesphere.data.movie
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
class MovieModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val plot: String,
    val poster: String,
    val watched: Boolean,
    val imdbID: String
)