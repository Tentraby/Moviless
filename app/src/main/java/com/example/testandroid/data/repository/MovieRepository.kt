package com.example.testandroid.data.repository

import com.example.testandroid.data.local.MovieDao
import com.example.testandroid.data.model.MovieType
import com.example.testandroid.data.model.toMovieEntityList
import com.example.testandroid.data.remote.RemoteDataSource
import com.example.testandroid.utils.performGetOperation
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val localDataSource: MovieDao,
    private val remoteDataSource: RemoteDataSource) {


    fun getPopularMovies() = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies(MovieType.POPULAR.value) },
        networkCall = { remoteDataSource.getPopularMovies() },
        saveCallResult = { localDataSource.insertAll(it.results.toMovieEntityList(MovieType.POPULAR.value)) }
    )
    fun getViewestMovies() = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies(MovieType.VIEWEST.value) },
        networkCall = { remoteDataSource.getViewestMovies() },
        saveCallResult = { localDataSource.insertAll(it.results.toMovieEntityList(MovieType.VIEWEST.value)) }
    )

    fun getUpcomingMovies() = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies(MovieType.UPCOMING.value) },
        networkCall = { remoteDataSource.getUpcomingMovies() },
        saveCallResult = { localDataSource.insertAll(it.results.toMovieEntityList(MovieType.UPCOMING.value)) }
    )
}