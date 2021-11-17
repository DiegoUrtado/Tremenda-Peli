package com.diegourtado.tremendapeli.presentation.tv_shows.view

import android.content.Context
import com.diegourtado.tremendapeli.base.BaseActivity
import com.diegourtado.tremendapeli.base.BasePresenter
import com.diegourtado.tremendapeli.data.cache.ImageCache
import com.diegourtado.tremendapeli.interactor.MoviesInteractor
import com.diegourtado.tremendapeli.presentation.movies.MoviesContract
import com.diegourtado.tremendapeli.presentation.movies.presenter.MoviesPresenter
import com.diegourtado.tremendapeli.presentation.tv_shows.TvShowsContract
import com.diegourtado.tremendapeli.presentation.tv_shows.presenter.TvShowPresenter


class TvShowsActivity : BaseActivity<TvShowPresenter>() , TvShowsContract.View {
    override fun showProgressBar() {
        TODO("Not yet implemented")
    }

    override fun hideProgressBar() {
        TODO("Not yet implemented")
    }

    override fun hideMovies() {
        TODO("Not yet implemented")
    }

    override fun showMovies() {
        TODO("Not yet implemented")
    }

    override fun fetchApodDetails() {
        TODO("Not yet implemented")
    }

    override fun showDataFetchError() {
        TODO("Not yet implemented")
    }

    override fun reloadData() {
        TODO("Not yet implemented")
    }

    override fun expandApodImage() {
        TODO("Not yet implemented")
    }

    override fun showApodImageProgressBar() {
        TODO("Not yet implemented")
    }

    override fun hideApodImageProgressBar() {
        TODO("Not yet implemented")
    }

    override fun createPresenter(context: Context): TvShowPresenter {
        TODO("Not yet implemented")
    }

}