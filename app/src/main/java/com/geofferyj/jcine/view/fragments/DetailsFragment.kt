package com.geofferyj.jcine.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.geofferyj.jcine.R
import com.geofferyj.jcine.models.repository.Repository
import com.geofferyj.jcine.utils.Constants
import com.geofferyj.jcine.utils.Resource
import com.geofferyj.jcine.view.MainActivity
import com.geofferyj.jcine.view.adapters.CastAdapter
import com.geofferyj.jcine.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_content.*
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var viewModel: MoviesViewModel

    private val args by navArgs<DetailsFragmentArgs>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        getMovieDetails(args.movieId)

        viewModel.movieDetails.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {

                    val castAdapter = CastAdapter()
                    loader.visibility = View.INVISIBLE
                    response.data?.let { movie ->

                        val image = "https://image.tmdb.org/t/p/w500${movie.backdropPath}"
                        Glide.with(requireContext())
                            .load(image)
                            .placeholder(R.drawable.cast_avatar)
                            .into(hero_image)
                        movie_name.text = movie.title
                        release_year.text = movie.releaseDate.take(4)
                        genre.text = movie.genres[0].name
                        duration.text = "${movie.runtime / 60}h ${movie.runtime % 60}min"
                        description.text = movie.overview
                        language.text = movie.spokenLanguages[0].name
                        imdb_rating.text = movie.rating.toString()

                        cast.adapter = castAdapter
                        castAdapter.differ.submitList(movie.credits.cast)

                    }

                }
                is Resource.Error -> {
                    response.message?.let {
                        Log.i(
                            Constants.RESPONSE_TAG,
                            "Error with message: $it"
                        )
                    }
                }
                is Resource.Loading -> {
                    loader.visibility = View.VISIBLE
                    Log.i(Constants.RESPONSE_TAG, "Loading Details")
                }
            }

        })

//        cast.adapter = RVAdapter(, this)

        toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }

    private fun getMovieDetails(id: Int) = GlobalScope.launch(Dispatchers.IO) {

        viewModel.movieDetails.postValue(Resource.Loading())
        val response = Repository().getMovieDetails(id)
        viewModel.movieDetails.postValue(viewModel.handleResponse(response))

    }

}