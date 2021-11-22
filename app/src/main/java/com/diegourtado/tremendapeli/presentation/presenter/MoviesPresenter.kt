package com.diegourtado.tremendapeli.presentation.presenter

import android.provider.SyncStateContract
import com.diegourtado.tremendapeli.base.BasePresenter
import com.diegourtado.tremendapeli.data.remote.MoviesSingleResponse
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.data.remote.ResultsItemTvShows
import com.diegourtado.tremendapeli.data.remote.TvShowsSingleResponse
import com.diegourtado.tremendapeli.interactor.Interactor
import com.diegourtado.tremendapeli.presentation.Contract
import com.diegourtado.tremendapeli.utils.Constants

class MoviesPresenter constructor(view : Contract.MoviesView, interactor : Interactor) : BasePresenter(), Contract.PresenterMovies {

    var view : Contract.MoviesView? = null
    var interactor: Interactor? = null

    init {
        this.view = view
        this.interactor = interactor
    }

    override fun fetchDataMovies(isPopular: Boolean) {
        view?.hideList()
        view?.showProgressBar()

        interactor?.getMoviesDataFromRemote(isPopular, 1, object : Interactor.OnMoviesListFetched{
            override fun onSuccess(results : List<ResultsItemMovies>) {
                view?.hideProgressBar()
                view?.showList()
                view?.onFetchMoviesSuccess(results)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }

    override fun fetchMoreDataMovies(isPopular: Boolean, page: Int) {
        view?.showProgressBar()

        interactor?.getMoviesDataFromRemote(isPopular, page, object : Interactor.OnMoviesListFetched{
            override fun onSuccess(results : List<ResultsItemMovies>) {
                view?.hideProgressBar()
                view?.onFetchMoreDataMoviesSuccess(isPopular, results)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }

    override fun searchMovies(query: String) {
        view?.showProgressBar()
        interactor?.searchMoviesFromRemote(query, object : Interactor.OnMoviesListFetched{
            override fun onSuccess(results : List<ResultsItemMovies>) {
                view?.hideProgressBar()
                view?.onSearchMovieSuccess(results)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }
}