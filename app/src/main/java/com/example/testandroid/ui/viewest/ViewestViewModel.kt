package com.example.testandroid.ui.viewest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testandroid.data.entities.MovieEntity
import com.example.testandroid.data.repository.MovieRepository
import com.example.testandroid.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ViewestViewModel @Inject constructor (private val repository: MovieRepository) : ViewModel() {
    val fetchViewestMovies: LiveData<Resource<List<MovieEntity>>> = repository.getViewestMovies()
}