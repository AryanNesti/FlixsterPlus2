package com.codepath.flixsterplus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = BuildConfig.API_KEY
const val MOVIE_EXTRA = "MOVIE_EXTRA"

/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the new postings api.
 */
class MoviesFragment : Fragment(), OnListFragmentInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_best_seller_books_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client[
            "https://api.themoviedb.org/3/movie/popular",
            params,
            object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    //TODO - Parse JSON into Models
                    val booksRawJSON = json.jsonObject.get("results").toString()
                    val gson = Gson()
                    val arrayBookType = object : TypeToken<List<Movie>>() {}.type
                    val models : List<Movie> = gson.fromJson(booksRawJSON, arrayBookType)

                    recyclerView.adapter = MoviesRecyclerViewAdapter(models, this@MoviesFragment)

                    // Look for this in Logcat:
                    Log.d("BestSellerBooksFragment", booksRawJSON)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    // If the error is not null, log it!
                t?.message?.let {
                    Log.e("BestSellerBooksFragment", errorResponse)
                }
            }
        }]


    }

    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(movie: Movie) {
        val intent = Intent(context, MovieDetail::class.java)
        intent.putExtra(MOVIE_EXTRA, movie)
        context?.startActivity(intent)
        Toast.makeText(context, "test: " + movie.title, Toast.LENGTH_LONG).show()
    }
    override fun onItemClick(tvShow: TvShow) {
        TODO("Not yet implemented")
    }

}
