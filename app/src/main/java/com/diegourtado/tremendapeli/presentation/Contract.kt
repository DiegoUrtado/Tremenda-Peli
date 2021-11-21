package com.diegourtado.tremendapeli.presentation

import com.diegourtado.tremendapeli.data.remote.*

interface Contract {

    interface View{

        fun showProgressBar()

        fun hideProgressBar()

        fun hideList()

        fun showList()

        fun onFetchMoviesSuccess(movies: List<ResultsItemMovies>)
        fun onFetchTvShowsSuccess(movies: List<ResultsItemTvShows>)

        fun onFetchDetailMovieSuccess(response: MovieResultItem)
        fun onFetchDetailTvShowSuccess(response: TvShowsSingleResponse)

        fun showDataFetchError()

        fun onFetchMoreDataSuccess(type: Int, movies: List<ResultsItemMovies>)

        fun showMoreDataFetchError(type: Int)

    }

    interface Presenter {
        fun fetchDetail(type: Int, id: Int)
        fun fetchData(type: Int)
        fun fetchMoreData(type: Int, page: Int)
    }

}