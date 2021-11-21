package com.diegourtado.tremendapeli.interactor

import com.diegourtado.tremendapeli.data.net.ApiService
import com.diegourtado.tremendapeli.data.remote.*
import com.diegourtado.tremendapeli.utils.Constants
import com.diegourtado.tremendapeli.utils.Constants.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Interactor {


    interface OnMoviesListFetched {
        fun onSuccess(results : List<ResultsItemMovies>)
        fun onFailure()
    }

    interface OnMovieDetailFetched {
        fun onSuccess(result : MoviesSingleResponse)
        fun onFailure()
    }

    interface OnTvShowsListFetched {
        fun onSuccess(results : List<ResultsItemTvShows>)
        fun onFailure()
    }

    interface OnTvShowDetailFetched {
        fun onSuccess(result : TvShowsSingleResponse)
        fun onFailure()
    }

    fun getMoviesDataFromRemote(type: Int, page: Int, listener: OnMoviesListFetched){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        var sort = Constants.SORT_POPULAR
        if (type == Constants.TYPE_MOVIES_TOP_RATED){
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

        val service = retrofit.create(ApiService::class.java)


        service.getMovieDetail(id, Constants.API_KEY)
            .enqueue(object : Callback<MoviesSingleResponse> {
                override fun onResponse(call: Call<MoviesSingleResponse>, response: Response<MoviesSingleResponse>) {
                    println("---response:"+response.raw())
                    listener.onSuccess((response.body()!! as MoviesSingleResponse?)!!)
                }

                override fun onFailure(call: Call<MoviesSingleResponse>, t: Throwable) {
                    println("onFailure")
                }
            })
    }


    fun getTvShowsDataFromRemote(type: Int, page: Int, listener: OnMoviesListFetched){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        var sort = Constants.SORT_POPULAR
        if (type == Constants.TYPE_TV_SHOWS_TOP_RATED){
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


    fun getTvShowDetailFromRemote(id: Int, listener: OnTvShowDetailFetched){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)


        service.getTvShowDetail(id, Constants.API_KEY)
            .enqueue(object : Callback<TvShowsSingleResponse> {
                override fun onResponse(call: Call<TvShowsSingleResponse>, response: Response<TvShowsSingleResponse>) {
                    println("---response:"+response.raw())
                    listener.onSuccess((response.body()!! as TvShowsSingleResponse?)!!)
                }

                override fun onFailure(call: Call<TvShowsSingleResponse>, t: Throwable) {
                    println("onFailure")
                }
            })
    }

}