package com.geofferyj.jcine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.geofferyj.jcine.adapters.HeroAdapter
import com.geofferyj.jcine.adapters.VPAdapter
import com.geofferyj.jcine.fragments.DramaFragment
import com.geofferyj.jcine.fragments.MoviesFragment
import com.geofferyj.jcine.fragments.ShowsFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf<Int>(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
        )

        val fragments = listOf<Fragment>(MoviesFragment(), ShowsFragment(), DramaFragment())

        hero_viewpager.adapter = HeroAdapter(images)
        TabLayoutMediator(hero_tabs, hero_viewpager){ _, _ ->
        }.attach()




        content_viewpager.adapter = VPAdapter(supportFragmentManager, lifecycle, fragments)
        TabLayoutMediator(content_tabLayout, content_viewpager){tab, position ->
            when(position){
                0 -> tab.text = "Movies"
                1 -> tab.text = "Shows"
                2 -> tab.text = "Drama"
            }
        }.attach()
    }
}