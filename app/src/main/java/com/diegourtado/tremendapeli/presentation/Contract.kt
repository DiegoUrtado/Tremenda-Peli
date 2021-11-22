package com.diegourtado.tremendapeli.presentation

import com.diegourtado.tremendapeli.data.remote.*

interface Contract {

    interface MoviesView{

        fun showProgressBar()

        fun hideProgressBar()

        fun hideList()

        fun showList()

        fun onFetchMoviesSuccess(movies: List<ResultsItemMovies>)

        fun showDataFetchError()

        fun onFetchMoreDataMoviesSuccess(isPopular: Boolean, movies: List<ResultsItemMovies>)

        fun showMoreDataFetchError(type: Int)

        fun onSearchMovieSuccess(movies: List<ResultsItemMovies>)

    }

    interface TvShowsView{

        fun showProgressBar()

        fun hideProgressBar()

        fun hideList()

        fun showList()

        fun onFetchTvShowsSuccess(tvShows: List<ResultsItemTvShows>)

        fun showDataFetchError()

        fun onFetchMoreDataTvShowsSuccess(isPopular: Boolean, tvShows: List<ResultsItemTvShows>)

        fun showMoreDataFetchError(type: Int)

        fun onSearchTvShowsSuccess(movies: List<ResultsItemTvShows>)

    }

    interface PresenterMovies {

        fun fetchDataMovies(isPopular: Boolean)

        fun fetchMoreDataMovies(isPopular: Boolean, page: Int)

        fun searchMovies(query: String)
    }

    interface PresenterTvShows {

        fun fetchDataTvShow(isPopular: Boolean)

        fun fetchMoreDataTvShow(isPopular: Boolean, page: Int)

        fun searchTvShows(query: String)
    }

    interface MovieDetailView{

        fun showProgressBar()

        fun hideProgressBar()

        fun onFetchDetailMovieSuccess(response: MovieResultItem)

        fun showDataFetchError()

        fun showNoDataError()

    }

    interface TvShowDetailView{

        fun showProgressBar()

        fun hideProgressBar()

        fun onFetchDetailTvShowSuccess(response: TvShowResultItem)

        fun showDataFetchError()

        fun showNoDataError()

    }

    interface PresenterDetailMovie {
        fun fetchDetail(id: Int)
    }

    interface PresenterDetailTvShow {
        fun fetchDetail(id: Int)
    }

}