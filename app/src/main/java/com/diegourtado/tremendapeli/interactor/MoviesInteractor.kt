package com.diegourtado.tremendapeli.interactor

import com.diegourtado.tremendapeli.data.net.MoviesApiService
import com.diegourtado.tremendapeli.data.remote.MovieDetailsResponse
import com.diegourtado.tremendapeli.data.remote.MoviesListResponse
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.utils.Constants
import com.diegourtado.tremendapeli.utils.Constants.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MoviesInteractor {

    companion object {

    }

    interface OnMoviesListFetched {
        fun onSuccess(results : List<ResultsItemMovies>)
        fun onFailure()
    }

    interface OnMovieDetailFetched {
        fun onSuccess(result : MovieDetailsResponse)
        fun onFailure()
    }


    fun getMoviesDataFromRemote(type: Int, page: Int, listener: OnMoviesListFetched){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MoviesApiService::class.java)

        var sort = Constants.SORT_POPULAR
        if (type == Constants.TYPE_TOP_RATED){
            sort = Constants.SORT_TOP_RATED
        }

        service.getMoviesData(Constants.API_KEY, sort, page)
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


    fun getMovieDetailFromRemote(id: Int, listener: OnMovieDetailFetched){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MoviesApiService::class.java)


        service.getMovieDetail(id, Constants.API_KEY)
            .enqueue(object : Callback<MovieDetailsResponse> {
                override fun onResponse(call: Call<MovieDetailsResponse>, response: Response<MovieDetailsResponse>) {
                    println("---response:"+response.raw())
                    listener.onSuccess((response.body()!! as MovieDetailsResponse?)!!)
                }

                override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                    println("onFailure")
                }
            })
    }

}