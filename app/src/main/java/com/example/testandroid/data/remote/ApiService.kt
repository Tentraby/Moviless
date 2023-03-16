package com.example.testandroid.data.remote

import com.example.testandroid.data.model.GetMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



    interface ApiService {
        //aqui esta el corazon intenta cambiar Response<GetMoviesResponse> por <>
        @GET("movie/popular")
        suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<GetMoviesResponse>

        @GET("movie/top_rated")
        suspend fun getViewestMovies(@Query("api_key") apiKey: String): Response<GetMoviesResponse>

        @GET("movie/upcoming")
        suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): Response<GetMoviesResponse>

}