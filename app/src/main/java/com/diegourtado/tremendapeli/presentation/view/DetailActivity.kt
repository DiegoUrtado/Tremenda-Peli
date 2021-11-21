package com.diegourtado.tremendapeli.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.base.BaseActivity
import com.diegourtado.tremendapeli.data.remote.*
import com.diegourtado.tremendapeli.interactor.Interactor
import com.diegourtado.tremendapeli.presentation.Contract
import com.diegourtado.tremendapeli.presentation.presenter.ItemPresenter
import com.diegourtado.tremendapeli.utils.Constants
import com.diegourtado.tremendapeli.utils.Util
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class DetailActivity : BaseActivity<ItemPresenter> (), Contract.View{

    override fun createPresenter(context: Context): ItemPresenter {
        return ItemPresenter(this, Interactor())
    }

    private var type: Int = 0
    private var id: Int = 0
    private var backdropPath: String = ""
    private var posterPath: String = ""
    private var releaseDate: String = ""
    private var title: String = ""

    private lateinit var youTubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Util.hideStatusBar(window)
        setContentView(R.layout.activity_detail)

        youTubePlayerView = findViewById(R.id.youtube_player_view)

        this.type = intent.getIntExtra(Constants.KEY_TYPE, 0)
        this.id = intent.getIntExtra(Constants.KEY_ID, 0)
        this.backdropPath = intent.getStringExtra(Constants.KEY_BACKDROP_PATH).toString()
        this.posterPath = intent.getStringExtra(Constants.KEY_POSTER_PATH).toString()
        this.releaseDate = intent.getStringExtra(Constants.KEY_RELEASE_DATE).toString()
        this.title = intent.getStringExtra(Constants.KEY_TITLE).toString()
        presenter.fetchDetail(this.type, this.id)
    }

    override fun showProgressBar() {
        findViewById<ProgressBar>(R.id.pb_movies).visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.pb_movies).visibility = View.GONE
    }

    override fun hideList() {
        TODO("Not yet implemented")
    }

    override fun showList() {
        TODO("Not yet implemented")
    }

    override fun onFetchMoviesSuccess(movies: List<ResultsItemMovies>) {
        TODO("Not yet implemented")
    }

    override fun onFetchTvShowsSuccess(movies: List<ResultsItemTvShows>) {
        TODO("Not yet implemented")
    }

    override fun onFetchDetailMovieSuccess(response: MovieResultItem) {
        Glide.with(this).load(Constants.URL_POSTER + this.posterPath).into(findViewById(R.id.iv_poster))
        youTubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                if (response.key != ""){
                    youTubePlayerView.visibility = View.VISIBLE
                    youTubePlayer.loadVideo(response.key!!,0f)
                }else{
                    //TODO
                    youTubePlayerView.visibility = View.GONE
                }
            }
        })
    }

    override fun onFetchDetailTvShowSuccess(response: TvShowsSingleResponse) {
        TODO("Not yet implemented")
    }

    override fun showDataFetchError() {
        TODO("Not yet implemented")
    }

    override fun onFetchMoreDataSuccess(type: Int, movies: List<ResultsItemMovies>) {
        TODO("Not yet implemented")
    }

    override fun showMoreDataFetchError(type: Int) {
        TODO("Not yet implemented")
    }

}