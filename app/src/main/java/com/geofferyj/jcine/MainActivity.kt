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


    }
}