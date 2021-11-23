package com.diegourtado.tremendapeli.presentation.presenter

import com.diegourtado.tremendapeli.base.BasePresenter
import com.diegourtado.tremendapeli.data.remote.ResultsItemTvShows
import com.diegourtado.tremendapeli.interactor.Interactor
import com.diegourtado.tremendapeli.presentation.Contract

class TvShowsPresenter constructor(view : Contract.TvShowsView, interactor : Interactor) : BasePresenter(), Contract.PresenterTvShows {

    var view : Contract.TvShowsView? = null
    var interactor: Interactor? = null

    init {
        this.view = view
        this.interactor = interactor
    }

    override fun fetchDataTvShow(isPopular: Boolean) {
        view?.hideList()
        view?.showProgressBar()

        interactor?.getTvShowsDataFromRemote(isPopular, 1, object : Interactor.OnTvShowsListFetched{
            override fun onSuccess(results : List<ResultsItemTvShows>) {
                view?.hideProgressBar()
                view?.showList()
                view?.onFetchTvShowsSuccess(results)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }

    override fun fetchMoreDataTvShow(isPopular: Boolean, page: Int) {
        view?.showProgressBar()

        interactor?.getTvShowsDataFromRemote(isPopular, page, object : Interactor.OnTvShowsListFetched{
            override fun onSuccess(results : List<ResultsItemTvShows>) {
                view?.hideProgressBar()
                view?.onFetchMoreDataTvShowsSuccess(isPopular, results)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }

    override fun searchTvShows(query: String) {
        view?.showProgressBar()
        interactor?.searchTvShowsFromRemote(query, object : Interactor.OnTvShowsListFetched{
            override fun onSuccess(results : List<ResultsItemTvShows>) {
                view?.hideProgressBar()
                view?.onSearchTvShowsSuccess(results)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }
}