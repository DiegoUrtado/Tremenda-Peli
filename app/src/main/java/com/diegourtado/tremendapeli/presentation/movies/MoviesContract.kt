package com.diegourtado.tremendapeli.presentation.movies

import com.diegourtado.tremendapeli.data.remote.MovieDetailsResponse
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.presentation.movies.model.Movie

interface MoviesContract {

    interface View{

        fun showProgressBar()

        fun hideProgressBar()

        fun hideMovies()

        fun showMovies()

        fun onFetchMoviesSuccess(type: Int, movies: List<ResultsItemMovies>)
        fun onFetchMovieDetailSuccess(response: MovieDetailsResponse)
        fun onFetchMoreMoviesSuccess(type: Int, movies: List<ResultsItemMovies>)

        fun showDataFetchError()
        fun showMoreDataFetchError(type: Int)

        fun reloadData()

    }

    interface Presenter {
        fun fetchMovieDetail(id: Int)
        fun fetchMoviesData(type: Int)
        fun fetchMoreMoviesData(type: Int, page: Int)
    }

}