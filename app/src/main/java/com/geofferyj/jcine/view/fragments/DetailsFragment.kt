package com.geofferyj.jcine.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.geofferyj.jcine.R
import com.geofferyj.jcine.utils.Constants
import com.geofferyj.jcine.utils.Resource
import com.geofferyj.jcine.view.MainActivity
import com.geofferyj.jcine.view.adapters.CastAdapter
import com.geofferyj.jcine.view.misc.ExpandableTextView.State.*
import com.geofferyj.jcine.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var viewModel: MoviesViewModel


    private val args by navArgs<DetailsFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        viewModel.networkConnection.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.getDetails(args.movieId, args.type)
            } else {
                viewModel.movieDetails.postValue(Resource.NetworkError("No Internet Connection"))
                viewModel.tvDetails.postValue(Resource.NetworkError("No Internet Connection"))
            }
        })



        if (args.type == "tv") {
            viewModel.tvDetails.observe(viewLifecycleOwner, { response ->
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
                            language.text = "English"
                            imdb_rating.text = "N/A"
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
                    is Resource.NetworkError -> {
                        Toast.makeText(requireContext(), "no internet", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        loader.visibility = View.VISIBLE
                        Log.i(Constants.RESPONSE_TAG, "Loading Details")
                    }
                }

            })
        } else {
            viewModel.movieDetails.observe(viewLifecycleOwner, { response ->
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
                            imdb_rating.text = "- ${movie.rating}/10"
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
                    is Resource.NetworkError -> {
                        Toast.makeText(requireContext(), "no internet", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        loader.visibility = View.VISIBLE
                        Log.i(Constants.RESPONSE_TAG, "Loading Details")
                    }
                }

            })
        }


        read_more.setOnClickListener {
            description.toggle()
            val view = it as TextView
            if (view.text == "Read More") {
                view.text = "Show Less"
            } else {
                view.text = "Read More"
            }

        }
        
        toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }


}