package com.codepath.flixsterplus

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class TvShowDetail : AppCompatActivity(){
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var abstractTextView: TextView
    private lateinit var popularityTextView: TextView
    private lateinit var releaseDateTextView: TextView
    private lateinit var ratingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tvshow_details)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.tvshowMediaImage)
        titleTextView = findViewById(R.id.tvshowMediaTitle)
        abstractTextView = findViewById(R.id.tvshowMediaAbstract)
        popularityTextView = findViewById(R.id.tvshowPopularity)
        releaseDateTextView = findViewById(R.id.tvshowReleaseDate)
        ratingTextView = findViewById(R.id.tvshowRating)

        // TODO: Get the extra from the Intent
        val tvshow = intent.getSerializableExtra(TVSHOW_EXTRA) as TvShow

        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = tvshow.title
        abstractTextView.text = tvshow.description
        popularityTextView.text = tvshow.adult.toString()
        releaseDateTextView.text = tvshow.releaseDate
        ratingTextView.text = tvshow.rating.toString() + "/10"

        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + tvshow.imagePosterPath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .transform(RoundedCorners(40))
            .into(mediaImageView)
    }
}