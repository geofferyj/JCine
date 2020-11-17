package com.geofferyj.jcine.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.geofferyj.jcine.R
import com.geofferyj.jcine.models.repository.Repository
import com.geofferyj.jcine.viewmodel.MoviesViewModel
import com.geofferyj.jcine.viewmodel.MoviesViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MoviesViewModelFactory(application, 0, repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


    }
}