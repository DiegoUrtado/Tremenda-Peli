package com.diegourtado.tremendapeli.presentation.tv_shows.presenter

import com.diegourtado.tremendapeli.base.BasePresenter
import com.diegourtado.tremendapeli.data.cache.ImageCache
import com.diegourtado.tremendapeli.interactor.MoviesInteractor
import com.diegourtado.tremendapeli.interactor.TvShowsInteractor
import com.diegourtado.tremendapeli.presentation.movies.MoviesContract
import com.diegourtado.tremendapeli.presentation.tv_shows.TvShowsContract



class TvShowPresenter constructor(view : TvShowsContract.View, tvShowInteractor : TvShowsInteractor, cache : ImageCache) : BasePresenter(), TvShowsContract.Presenter {
    override fun fetchTvShowsData() {
        TODO("Not yet implemented")
    }
}