package com.geofferyj.jcine.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.geofferyj.jcine.R
import com.geofferyj.jcine.utils.Constants
import com.geofferyj.jcine.utils.Resource
import com.geofferyj.jcine.view.MainActivity
import com.geofferyj.jcine.view.adapters.MovieAdapter
import com.geofferyj.jcine.viewmodel.MoviesViewModel
import com.geofferyj.jcine.viewmodel.MoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_content.*


class DramaFragment : Fragment(R.layout.fragment_content) {

    private lateinit var viewModel: MoviesViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        viewModel.moviesComingSoon.observe(viewLifecycleOwner, Observer {response ->

            when(response){
                is Resource.Success -> {
                    rv_coming_soon.adapter = MovieAdapter().also {
                        it.differ.submitList(response.data?.movies)
                    }

                }
                is Resource.Error -> {
                    response.message?.let { Log.i(Constants.RESPONSE_TAG, "Error with message: $it") }
                }
                is Resource.NetworkError -> {
                    Toast.makeText(requireContext(), "no internet", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    Log.i(Constants.RESPONSE_TAG, "Loading")
                }
            }

        })


        viewModel.moviesPopular.observe(viewLifecycleOwner, Observer {response ->

            when(response){
                is Resource.Success -> {
                    rv_popular.adapter = MovieAdapter().also {
                        it.differ.submitList(response.data?.movies)
                    }

                }
                is Resource.Error -> {
                    response.message?.let { Log.i(Constants.RESPONSE_TAG, "Error with message: $it") }
                }
                is Resource.NetworkError -> {
                    Toast.makeText(requireContext(), "no internet", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    Log.i(Constants.RESPONSE_TAG, "Loading")
                }
            }

        })


        viewModel.moviesNewRelease.observe(viewLifecycleOwner, Observer {response ->

            when(response){
                is Resource.Success -> {
                    rv_new_release.adapter = MovieAdapter().also {
                        it.differ.submitList(response.data?.movies)
                    }

                }
                is Resource.Error -> {
                    response.message?.let { Log.i(Constants.RESPONSE_TAG, "Error with message: $it") }
                }
                is Resource.NetworkError -> {
                    Toast.makeText(requireContext(), "no internet", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    Log.i(Constants.RESPONSE_TAG, "Loading")
                }
            }

        })
    }

}