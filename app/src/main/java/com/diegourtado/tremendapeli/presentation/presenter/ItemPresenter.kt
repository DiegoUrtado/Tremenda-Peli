package com.diegourtado.tremendapeli.presentation.presenter

import android.provider.SyncStateContract
import com.diegourtado.tremendapeli.base.BasePresenter
import com.diegourtado.tremendapeli.data.remote.MoviesSingleResponse
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.data.remote.TvShowsSingleResponse
import com.diegourtado.tremendapeli.interactor.Interactor
import com.diegourtado.tremendapeli.presentation.Contract
import com.diegourtado.tremendapeli.utils.Constants

class ItemPresenter constructor(view : Contract.View, interactor : Interactor) : BasePresenter(), Contract.Presenter {

    var view : Contract.View? = null
    var interactor: Interactor? = null

    init {
        this.view = view
        this.interactor = interactor
    }

    override fun fetchDetail(type: Int, id: Int) {
        view?.showProgressBar()

        when (type){
            Constants.TYPE_MOVIES_POPULAR -> fetchMovieDetail(id)
            Constants.TYPE_MOVIES_TOP_RATED -> fetchMovieDetail(id)
            Constants.TYPE_TV_SHOWS_POPULAR -> fetchTvShowDetail(id)
            Constants.TYPE_TV_SHOWS_TOP_RATED -> fetchTvShowDetail(id)
        }

    }

    private fun fetchMovieDetail(id: Int){
        interactor?.getMovieDetailFromRemote(id, object : Interactor.OnMovieDetailFetched{
            override fun onSuccess(response : MoviesSingleResponse) {
                view?.hideProgressBar()
                view?.showList()
                view?.onFetchDetailMovieSuccess(response!!.results!![0]!!)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }


    private fun fetchTvShowDetail(id: Int){
        interactor?.getTvShowDetailFromRemote(id, object : Interactor.OnTvShowDetailFetched{
            override fun onSuccess(response : TvShowsSingleResponse) {
                view?.hideProgressBar()
                view?.showList()
                view?.onFetchDetailTvShowSuccess(response)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }

    override fun fetchData(type: Int) {
        view?.hideList()
        view?.showProgressBar()

        interactor?.getMoviesDataFromRemote(type, 1, object : Interactor.OnMoviesListFetched{
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

    override fun fetchMoreData(type: Int, page: Int) {
        view?.showProgressBar()

        interactor?.getMoviesDataFromRemote(type, page, object : Interactor.OnMoviesListFetched{
            override fun onSuccess(results : List<ResultsItemMovies>) {
                view?.hideProgressBar()
                view?.onFetchMoreDataSuccess(type, results)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }
}