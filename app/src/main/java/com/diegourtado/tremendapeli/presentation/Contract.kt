package com.diegourtado.tremendapeli.presentation

import com.diegourtado.tremendapeli.data.remote.*

interface Contract {

    interface View{

        fun showProgressBar()

        fun hideProgressBar()

        fun hideList()

        fun showList()

        fun onFetchMoviesSuccess(movies: List<ResultsItemMovies>)
        fun onFetchTvShowsSuccess(tvShows: List<ResultsItemTvShows>)

        fun onFetchDetailMovieSuccess(response: MovieResultItem)
        fun onFetchDetailTvShowSuccess(response: TvShowsSingleResponse)

        fun showDataFetchError()

        fun onFetchMoreDataMoviesSuccess(isPopular: Boolean, movies: List<ResultsItemMovies>)
        fun onFetchMoreDataTvShowsSuccess(isPopular: Boolean, tvShows: List<ResultsItemTvShows>)

        fun showMoreDataFetchError(type: Int)

        fun onSearchMovieSuccess(movies: List<ResultsItemMovies>)
        fun onSearchTvShowsSuccess(movies: List<ResultsItemTvShows>)

    }

    interface Presenter {
        fun fetchDetail(type: Int, id: Int)

        fun fetchDataMovies(isPopular: Boolean)
        fun fetchMoreDataMovies(isPopular: Boolean, page: Int)

        fun fetchDataTvShow(isPopular: Boolean)
        fun fetchMoreDataTvShow(isPopular: Boolean, page: Int)

        fun searchMovies(query: String)
        fun searchTvShows(query: String)
    }

}