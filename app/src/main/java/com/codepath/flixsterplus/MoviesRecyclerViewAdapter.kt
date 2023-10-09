package com.codepath.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.codepath.flixsterplus.R.id


/**
 * [RecyclerView.Adapter] that can display a [Movie] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MoviesRecyclerViewAdapter(
    private val books: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
    )
    : RecyclerView.Adapter<`MoviesRecyclerViewAdapter`.BookViewHolder>()
    {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
        val mMoviePoster: ImageView = mView.findViewById<View>(id.movie_poster) as ImageView
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val movie = books[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500" + movie.imagePosterPath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .transform(RoundedCorners(30))
            .into(holder.mMoviePoster)

        holder.mView.setOnClickListener {
            holder.mItem?.let { mv ->
                mListener?.onItemClick(mv)
            }
        }
    }


    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return books.size
    }

}