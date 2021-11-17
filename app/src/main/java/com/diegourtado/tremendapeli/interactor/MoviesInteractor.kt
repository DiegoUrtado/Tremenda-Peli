package com.diegourtado.tremendapeli.interactor

import com.diegourtado.tremendapeli.data.net.MoviesApiService
import com.diegourtado.tremendapeli.data.remote.MoviesListResponse
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MoviesInteractor {

    companion object {
        const val API_KEY: String = "155446d57fd2ec28616d19685b76fbfb"
        const val BASE_URL: String = "https://api.themoviedb.org/3/discover/"
        const val SORT_POPULAR: String = "popularity.desc"
        const val SORT_TOP_RATED: String = "top_rated"
    }

    interface OnMoviesListFetched {
        fun onSuccess(results : List<ResultsItemMovies>)
        fun onFailure()
    }

    fun getMoviesDataFromRemote(type: Int, page: Int, listener: OnMoviesListFetched){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MoviesApiService::class.java)

        var SORT = SORT_POPULAR
        if (type == Constants.TYPE_TOP_RATED){
            SORT = SORT_TOP_RATED
        }

        service.getMoviesData(API_KEY, SORT, page)
            .enqueue(object : Callback<MoviesListResponse> {
                override fun onResponse(call: Call<MoviesListResponse>, response: Response<MoviesListResponse>) {
                    println("---response:"+response.raw())
                    listener.onSuccess((response.body()!!.results as List<ResultsItemMovies>?)!!)
                }

                override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                    println("onFailure")
                }
            })
    }

}