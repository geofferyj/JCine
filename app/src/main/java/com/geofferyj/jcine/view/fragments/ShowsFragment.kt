package com.geofferyj.jcine.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
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

class ShowsFragment : Fragment(R.layout.fragment_content) {

    private lateinit var viewModel: MoviesViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).viewModel


        viewModel.tvNewRelease.observe(viewLifecycleOwner, Observer { response ->
            val rvAdapter = MovieAdapter()
            when (response) {
                is Resource.Success -> {
                    rv_new_release.adapter = rvAdapter
                    rvAdapter.differ.submitList(response.data?.movies)

                    rvAdapter.setOnItemClickListener {
                        val action =
                            FirstPageFragmentDirections.actionFirstPageFragmentToDetailsFragment(it.id)
                        findNavController().navigate(action)


                    }
                }
                is Resource.Error -> {
                }
                is Resource.Loading -> {
                    Log.i(Constants.RESPONSE_TAG, "Loading")
                }
            }

        })
        viewModel.tvComingSoon.observe(viewLifecycleOwner, Observer { response ->
            val rvAdapter = MovieAdapter()
            when (response) {
                is Resource.Success -> {
                    rv_coming_soon.adapter = rvAdapter
                    rvAdapter.differ.submitList(response.data?.movies)

                    rvAdapter.setOnItemClickListener {
                        val action =
                            FirstPageFragmentDirections.actionFirstPageFragmentToDetailsFragment(it.id)
                        findNavController().navigate(action)


                    }
                }
                is Resource.Error -> {
                }
                is Resource.Loading -> {
                    Log.i(Constants.RESPONSE_TAG, "Loading")
                }
            }

        })
        viewModel.tvPopular.observe(viewLifecycleOwner, Observer { response ->
            val rvAdapter = MovieAdapter()
            when (response) {
                is Resource.Success -> {
                    rv_popular.adapter = rvAdapter
                    rvAdapter.differ.submitList(response.data?.movies)

                    rvAdapter.setOnItemClickListener {
                        val action =
                            FirstPageFragmentDirections.actionFirstPageFragmentToDetailsFragment(it.id)
                        findNavController().navigate(action)


                    }
                }
                is Resource.Error -> {
                }
                is Resource.Loading -> {
                    Log.i(Constants.RESPONSE_TAG, "Loading")
                }
            }

        })



    }

}