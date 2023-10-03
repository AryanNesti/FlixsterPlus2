package com.codepath.flixsterplus


import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a movie
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Movie {

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("poster_path")

    var imagePosterPath: String? = null

    @JvmField
    @SerializedName("overview")
    var description: String? = null

    @JvmField
    @SerializedName("backdrop_path")
    var backdrop: String? = null

}