package com.diegourtado.tremendapeli.presentation.presenter

import com.diegourtado.tremendapeli.base.BasePresenter
import com.diegourtado.tremendapeli.data.remote.MoviesSingleResponse
import com.diegourtado.tremendapeli.data.remote.TvShowsSingleResponse
import com.diegourtado.tremendapeli.interactor.Interactor
import com.diegourtado.tremendapeli.presentation.Contract

class TvShowDetailPresenter constructor(view : Contract.TvShowDetailView, interactor : Interactor) : BasePresenter(), Contract.PresenterDetailTvShow {

    var view : Contract.TvShowDetailView? = null
    var interactor: Interactor? = null

    init {
        this.view = view
        this.interactor = interactor
    }

    override fun fetchDetail(id: Int) {
        view?.showProgressBar()

        interactor?.getTvShowDetailFromRemote(id, object : Interactor.OnTvShowDetailFetched{
            override fun onSuccess(response : TvShowsSingleResponse) {
                view?.hideProgressBar()
                if(response.results!!.size > 0){
                    view?.onFetchDetailTvShowSuccess(response!!.results!![0]!!)
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