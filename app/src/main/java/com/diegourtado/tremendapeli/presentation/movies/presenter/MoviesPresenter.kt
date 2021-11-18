package com.diegourtado.tremendapeli.presentation.movies.presenter

import com.diegourtado.tremendapeli.base.BasePresenter
import com.diegourtado.tremendapeli.data.cache.ImageCache
import com.diegourtado.tremendapeli.data.cache.ImageCacheImpl
import com.diegourtado.tremendapeli.data.remote.MovieDetailsResponse
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.interactor.MoviesInteractor
import com.diegourtado.tremendapeli.presentation.movies.MoviesContract

class MoviesPresenter constructor(view : MoviesContract.View, moviesInteractor : MoviesInteractor, cache : ImageCacheImpl) : BasePresenter(), MoviesContract.Presenter {

    var view : MoviesContract.View? = null
    var moviesInteractor: MoviesInteractor? = null
    //TODO("sacar esto")
    var cache: ImageCacheImpl? = null

    init {
        this.view = view
        this.moviesInteractor = moviesInteractor
        this.cache = cache
    }

    override fun fetchMovieDetail(id: Int) {
        view?.showProgressBar()

        moviesInteractor?.getMovieDetailFromRemote(id, object : MoviesInteractor.OnMovieDetailFetched{
            override fun onSuccess(response : MovieDetailsResponse) {
                view?.hideProgressBar()
                view?.showMovies()
                view?.onFetchMovieDetailSuccess(response)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }

    override fun fetchMoviesData(type: Int) {
        view?.hideMovies()
        view?.showProgressBar()

        moviesInteractor?.getMoviesDataFromRemote(type, 1, object : MoviesInteractor.OnMoviesListFetched{
            override fun onSuccess(results : List<ResultsItemMovies>) {
                view?.hideProgressBar()
                view?.showMovies()
                view?.onFetchMoviesSuccess(type, results)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }

    override fun fetchMoreMoviesData(type: Int, page: Int) {
        view?.showProgressBar()

        moviesInteractor?.getMoviesDataFromRemote(type, page, object : MoviesInteractor.OnMoviesListFetched{
            override fun onSuccess(results : List<ResultsItemMovies>) {
                view?.hideProgressBar()
                view?.onFetchMoreMoviesSuccess(type, results)
            }

            override fun onFailure() {
                view?.hideProgressBar()
                view?.showDataFetchError()
            }
        })
    }

}