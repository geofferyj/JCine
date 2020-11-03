package com.geofferyj.jcine.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geofferyj.jcine.R
import com.geofferyj.jcine.adapters.HeroAdapter
import com.geofferyj.jcine.adapters.VPAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_first_page.*

class FirstPageFragment : Fragment(R.layout.fragment_first_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = listOf<Int>(
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
        )

        val fragments = listOf<Fragment>(MoviesFragment(), ShowsFragment(), DramaFragment())

        hero_viewpager.adapter = HeroAdapter(images)
        TabLayoutMediator(hero_tabs, hero_viewpager){ _, _ ->
        }.attach()




        content_viewpager.adapter = VPAdapter(childFragmentManager, lifecycle, fragments)
        TabLayoutMediator(content_tabLayout, content_viewpager){tab, position ->
            when(position){
                0 -> tab.text = "Movies"
                1 -> tab.text = "Shows"
                2 -> tab.text = "Drama"
            }
        }.attach()
    }
}