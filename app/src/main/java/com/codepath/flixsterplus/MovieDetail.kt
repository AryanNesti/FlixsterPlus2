package com.codepath.flixsterplus

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

private const val TAG = "DetailActivity"
class MovieDetail : AppCompatActivity(){
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var abstractTextView: TextView
    private lateinit var popularityTextView: TextView
    private lateinit var releaseDateTextView: TextView
    private lateinit var ratingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.movieMediaImage)
        titleTextView = findViewById(R.id.movieMediaTitle)
        abstractTextView = findViewById(R.id.movieMediaAbstract)
        popularityTextView = findViewById(R.id.moviePopularity)
        releaseDateTextView = findViewById(R.id.movieReleaseDate)
        ratingTextView = findViewById(R.id.movieRating)


        // TODO: Get the extra from the Intent
        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = movie.title
        abstractTextView.text = movie.description
        popularityTextView.text = movie.adult.toString()
        releaseDateTextView.text = movie.releaseDate
        ratingTextView.text = movie.rating.toString() + "/10"

        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.imagePosterPath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .transform(RoundedCorners(40))
            .into(mediaImageView)
    }
}