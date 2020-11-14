package com.geofferyj.jcine.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geofferyj.jcine.models.repository.Repository

class MoviesViewModelFactory(
    val application: Application,
    var id: Int,
    val repository: Repository
) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(application, repository = repository) as T


    }
}