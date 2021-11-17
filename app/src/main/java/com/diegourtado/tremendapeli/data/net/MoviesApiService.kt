package com.diegourtado.tremendapeli.data.net

import com.diegourtado.tremendapeli.data.remote.MoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {


    @GET("movie")
    fun getMoviesData(
        @Query("api_key") API_KEY: String,
        @Query("sort_by") SORT_BY: String?,
        @Query("page") PAGE: Int?
    ): Call<MoviesListResponse>

}