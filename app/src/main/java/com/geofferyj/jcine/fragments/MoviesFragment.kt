package com.geofferyj.jcine.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.geofferyj.jcine.misc.Movie
import com.geofferyj.jcine.R
import com.geofferyj.jcine.adapters.RVAdapter
import kotlinx.android.synthetic.main.fragment_content.*


class MoviesFragment : Fragment(R.layout.fragment_content) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieImage:Int = R.drawable.image3
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

        rv_coming_soon.adapter = RVAdapter(movies)
        rv_new_release.adapter = RVAdapter(movies)
        rv_popular.adapter = RVAdapter(movies)
    }

}