package com.diegourtado.tremendapeli.presentation.presenter

import com.diegourtado.tremendapeli.base.BasePresenter
import com.diegourtado.tremendapeli.data.remote.MoviesSingleResponse
import com.diegourtado.tremendapeli.interactor.Interactor
import com.diegourtado.tremendapeli.presentation.Contract

class MovieDetailPresenter constructor(view : Contract.MovieDetailView, interactor : Interactor) : BasePresenter(), Contract.PresenterDetailMovie {

    var view : Contract.MovieDetailView? = null
    var interactor: Interactor? = null

    init {
        this.view = view
        this.interactor = interactor
    }

    override fun fetchDetail(id: Int) {
        view?.showProgressBar()

        interactor?.getMovieDetailFromRemote(id, object : Interactor.OnMovieDetailFetched{
            override fun onSuccess(response : MoviesSingleResponse) {
                view?.hideProgressBar()
                if(response.results!!.isNotEmpty()){
                    view?.onFetchDetailMovieSuccess(response.results[0]!!)
                }else{
                    view?.showNoDataError()
                }
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }
}