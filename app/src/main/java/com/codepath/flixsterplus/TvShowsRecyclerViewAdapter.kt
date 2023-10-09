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

class TvShowsRecyclerViewAdapter (
    private val tvshows: List<TvShow>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<`TvShowsRecyclerViewAdapter`.BookViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_tvshow, parent, false)
            return BookViewHolder(view)
        }

        /**
         * This inner class lets us refer to all the different View elements
         * (Yes, the same ones as in the XML layout files!)
         */
        inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            var mItem: TvShow? = null
            val mTvShowTitle: TextView = mView.findViewById<View>(id.tvShow_title) as TextView
            val mTvShowPoster: ImageView = mView.findViewById<View>(id.tvShow_poster) as ImageView
        }

        /**
         * This lets us "bind" each Views in the ViewHolder to its' actual data!
         */

        override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
            val tvshow = tvshows[position]

            holder.mItem = tvshow
            holder.mTvShowTitle.text = tvshow.title
            Glide.with(holder.mView)
                .load("https://image.tmdb.org/t/p/w500" + tvshow.imagePosterPath)
                .placeholder(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .transform(RoundedCorners(30))
                .into(holder.mTvShowPoster)

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
            return tvshows.size
        }
}