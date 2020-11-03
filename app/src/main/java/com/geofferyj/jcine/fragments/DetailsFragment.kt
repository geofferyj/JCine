package com.geofferyj.jcine.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.geofferyj.jcine.R
import com.geofferyj.jcine.adapters.RVAdapter
import com.geofferyj.jcine.misc.Movie
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment(R.layout.fragment_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieImage:Int = R.drawable.imdb_play_icon
        val movies: List<Movie> = listOf(
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
            Movie("Movie One", movieImage),
        )

        cast.adapter = RVAdapter(movies)
    }
}