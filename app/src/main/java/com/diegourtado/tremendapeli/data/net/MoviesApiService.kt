package com.diegourtado.tremendapeli.data.net

import com.diegourtado.tremendapeli.data.remote.MovieDetailsResponse
import com.diegourtado.tremendapeli.data.remote.MoviesListResponse
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {


    @GET("discover/movie")
    fun getMoviesData(
        @Query("api_key") API_KEY: String,
        @Query("sort_by") SORT_BY: String?,
        @Query("page") PAGE: Int?
    ): Call<MoviesListResponse>


    @GET("movie/{movie_id}/videos")
    fun getMovieDetail(@Path("movie_id") movieId: Int, @Query("api_key") API_KEY: String
        ): Call<MovieDetailsResponse>


}

