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
import com.geofferyj.jcine.view.adapters.HeroAdapter
import com.geofferyj.jcine.view.adapters.MovieAdapter
import com.geofferyj.jcine.view.adapters.VPAdapter
import com.geofferyj.jcine.viewmodel.MoviesViewModel
import com.geofferyj.jcine.viewmodel.MoviesViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_content.*
import kotlinx.android.synthetic.main.fragment_first_page.*

class FirstPageFragment : Fragment(R.layout.fragment_first_page) {
    private lateinit var viewModel: MoviesViewModel
    lateinit var hAdapter: HeroAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        val fragments = listOf<Fragment>(MoviesFragment(), ShowsFragment(), DramaFragment())

        hAdapter = HeroAdapter()
        hero_viewpager.adapter = hAdapter
        TabLayoutMediator(hero_tabs, hero_viewpager) { _, _ ->
        }.attach()


        viewModel.moviesTrending.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
                    loader.visibility = View.INVISIBLE
                    response.data?.let {

                        hAdapter.differ.submitList(it.movies.take(5))

                        hAdapter.setOnItemClickListener { movie ->
                            val action =
                                FirstPageFragmentDirections.actionFirstPageFragmentToDetailsFragment(
                                    movie.id
                                )
                            findNavController().navigate(action)


                        }
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
                }
                is Resource.NetworkError -> {
                    Toast.makeText(requireContext(), "no internet", Toast.LENGTH_SHORT).show()
                }
            }

        })



        content_viewpager.adapter = VPAdapter(childFragmentManager, lifecycle, fragments)
        TabLayoutMediator(content_tabLayout, content_viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = "Movies"
                1 -> tab.text = "Shows"
                2 -> tab.text = "Drama"
            }
        }.attach()


    }
}