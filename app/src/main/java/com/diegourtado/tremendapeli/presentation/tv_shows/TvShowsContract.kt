package com.diegourtado.tremendapeli.presentation.tv_shows

interface TvShowsContract {

    interface View{

        fun showProgressBar()

        fun hideProgressBar()

        fun hideMovies()

        fun showMovies()

        //fun showMovieDetails(movieData: Movie)

        fun fetchApodDetails()

        fun showDataFetchError()

        fun reloadData()

        fun expandApodImage()

        fun showApodImageProgressBar()

        fun hideApodImageProgressBar()
    }

    interface Presenter {

        fun fetchTvShowsData()
    }


}