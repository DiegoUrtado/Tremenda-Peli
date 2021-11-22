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

    fun getMoviesDataFromRemote(isPopular: Boolean, page: Int, listener: OnMoviesListFetched){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        var sort = Constants.SORT_TOP_RATED
        if (isPopular){
            sort = Constants.SORT_POPULAR
        }

        service.getMoviesData(Constants.API_KEY, sort, page)
            .enqueue(object : Callback<MoviesListResponse> {
                override fun onResponse(call: Call<MoviesListResponse>, response: Response<MoviesListResponse>) {
                    println("---response:"+response.raw())
                    listener.onSuccess((response.body()!!.results as List<ResultsItemMovies>?)!!)
                }

                override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                    listener.onFailure()
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
                    listener.onFailure()
                }
            })
    }


    fun getTvShowsDataFromRemote(isPopular: Boolean, page: Int, listener: OnTvShowsListFetched){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        var sort = Constants.SORT_TOP_RATED
        if (isPopular){
            sort = Constants.SORT_POPULAR
        }

        service.getTvShowsData(Constants.API_KEY, sort, page)
            .enqueue(object : Callback<TvShowsListResponse> {
                override fun onResponse(call: Call<TvShowsListResponse>, response: Response<TvShowsListResponse>) {
                    println("---response:"+response.raw())
                    listener.onSuccess((response.body()!!.results as List<ResultsItemTvShows>?)!!)
                }

                override fun onFailure(call: Call<TvShowsListResponse>, t: Throwable) {
                    listener.onFailure()
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
                    listener.onFailure()
                }
            })
    }

    fun searchMoviesFromRemote(query: String, listener: OnMoviesListFetched){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)


        service.searchMovies(query, Constants.API_KEY)
            .enqueue(object : Callback<MoviesListResponse> {
                override fun onResponse(call: Call<MoviesListResponse>, response: Response<MoviesListResponse>) {
                    println("---response:"+response.raw())
                    listener.onSuccess((response.body()!!.results as List<ResultsItemMovies>?)!!)
                }

                override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                    listener.onFailure()
                }
            })
    }

    fun searchTvShowsFromRemote(query: String, listener: OnTvShowsListFetched){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)


        service.searchTvShows(query, Constants.API_KEY)
            .enqueue(object : Callback<TvShowsListResponse> {
                override fun onResponse(call: Call<TvShowsListResponse>, response: Response<TvShowsListResponse>) {
                    println("---response:"+response.raw())
                    listener.onSuccess((response.body()!!.results as List<ResultsItemTvShows>?)!!)
                }

                override fun onFailure(call: Call<TvShowsListResponse>, t: Throwable) {
                    listener.onFailure()
                }
            })
    }
}