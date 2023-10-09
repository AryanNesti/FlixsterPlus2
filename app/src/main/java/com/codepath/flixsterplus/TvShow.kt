package com.codepath.flixsterplus

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
@Keep
@Serializable
data class TvShow (
    @JvmField
    @SerializedName("name")
    var title: String? = null,

    @JvmField
    @SerializedName("backdrop_path")
    var imageBackdropPath: String? = null,

    @JvmField
    @SerializedName("poster_path")
    var imagePosterPath: String? = null,

    @JvmField
    @SerializedName("overview")
    var description: String? = null,

    @JvmField
    @SerializedName("first_air_date")
    var releaseDate: String? = null,

    @JvmField
    @SerializedName("adult")
    var adult: Boolean? = null,

    @JvmField
    @SerializedName("vote_average")
    var rating: String? = null

    ) : java.io.Serializable