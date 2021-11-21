package com.diegourtado.tremendapeli.data.net


import com.diegourtado.tremendapeli.data.remote.MoviesListResponse
import com.diegourtado.tremendapeli.data.remote.MoviesSingleResponse
import com.diegourtado.tremendapeli.data.remote.TvShowsListResponse
import com.diegourtado.tremendapeli.data.remote.TvShowsSingleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("discover/movie")
    fun getMoviesData(
        @Query("api_key") API_KEY: String,
        @Query("sort_by") SORT_BY: String?,
        @Query("page") PAGE: Int?
    ): Call<MoviesListResponse>

    @GET("movie/{movie_id}/videos")
    fun getMovieDetail(@Path("movie_id") movieId: Int, @Query("api_key") API_KEY: String
    ): Call<MoviesSingleResponse>

    @GET("discover/tv_show")
    fun getTvShowsData(
        @Query("api_key") API_KEY: String,
        @Query("sort_by") SORT_BY: String?,
        @Query("page") PAGE: Int?
    ): Call<TvShowsListResponse>

    @GET("tv_shows/{tv_show_id}/videos")
    fun getTvShowDetail(@Path("tv_show_id") tvShowId: Int, @Query("api_key") API_KEY: String
    ): Call<TvShowsSingleResponse>



}

