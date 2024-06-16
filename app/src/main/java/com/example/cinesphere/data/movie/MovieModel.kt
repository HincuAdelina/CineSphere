package com.example.cinesphere.data.movie
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable

@Entity(tableName = "movies")
class MovieModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val plot: String,
    val poster: String,
    var watched: Boolean = false,
    val imdbID: String
) : Serializable