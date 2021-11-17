package com.diegourtado.tremendapeli.presentation.movies

import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.presentation.movies.model.Movie

interface MoviesContract {

    interface View{

        fun showProgressBar()

        fun hideProgressBar()

        fun hideMovies()

        fun showMovies()

        fun onFetchMoviesSuccess(type: Int, movies: List<ResultsItemMovies>)
        fun onFetchMoreMoviesSuccess(type: Int, movies: List<ResultsItemMovies>)

        fun showDataFetchError()
        fun showMoreDataFetchError(type: Int)

        fun reloadData()

    }

    interface Presenter {
        fun fetchMoviesData(type: Int)
        fun fetchMoreMoviesData(type: Int, page: Int)
    }

}