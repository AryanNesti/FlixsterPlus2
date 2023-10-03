package com.codepath.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
            .inflate(R.layout.fragment_best_seller_book, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mBookTitle: TextView = mView.findViewById<View>(id.book_title) as TextView
        val mBookDescription: TextView = mView.findViewById<View>(id.book_description) as TextView
        val mBookImage: ImageView = mView.findViewById<View>(id.book_image) as ImageView
        val moviePoster: ImageView = mView.findViewById<View>(id.moviePoster) as ImageView
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.mItem = book
        holder.mBookTitle.text = book.title
        holder.mBookDescription.text = book.description

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500" + book.imagePosterPath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerInside()
            .into(holder.mBookImage)

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500" + book.backdrop)
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerInside()
            .into(holder.moviePoster)

        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
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