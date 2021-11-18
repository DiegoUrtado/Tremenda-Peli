package com.diegourtado.tremendapeli.presentation.movies.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.utils.widget.MockView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.base.BaseActivity
import com.diegourtado.tremendapeli.data.cache.ImageCacheImpl
import com.diegourtado.tremendapeli.data.remote.MovieDetailsResponse
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.interactor.MoviesInteractor
import com.diegourtado.tremendapeli.presentation.movies.MoviesContract
import com.diegourtado.tremendapeli.presentation.movies.model.Movie
import com.diegourtado.tremendapeli.presentation.movies.presenter.MoviesPresenter
import com.diegourtado.tremendapeli.presentation.movies.view.adapter.MovieListViewHolder
import com.diegourtado.tremendapeli.presentation.movies.view.adapter.MoviesListAdapter
import com.diegourtado.tremendapeli.utils.Constants
import com.diegourtado.tremendapeli.utils.Util

class MoviesActivity : BaseActivity<MoviesPresenter>() , MoviesContract.View {

    override fun createPresenter(context: Context) : MoviesPresenter {
        return MoviesPresenter(this, MoviesInteractor(), ImageCacheImpl(this))
    }

    lateinit var popularMoviesAdapter: MoviesListAdapter
    lateinit var topRatedMoviesAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Util.hideStatusBar(window)
        setContentView(R.layout.activity_movies)

        initRecyclerView()

        presenter.fetchMoviesData(Constants.TYPE_POPULAR)
        presenter.fetchMoviesData(Constants.TYPE_TOP_RATED)
    }

    private fun initRecyclerView() {
        this.topRatedMoviesAdapter = MoviesListAdapter(Constants.TYPE_TOP_RATED) { movie ->
            showDetails(movie)
        }
        this.popularMoviesAdapter = MoviesListAdapter(Constants.TYPE_POPULAR){ movie ->
            showDetails(movie)
        }
        setUpAdapter(this.popularMoviesAdapter, R.id.rv_movies_popular)
        setUpAdapter(this.topRatedMoviesAdapter, R.id.rv_movies_top_rated)
    }

    private fun showDetails(movie: ResultsItemMovies){
        val i = Intent(this, MovieDetailActivity::class.java)
        i.putExtra( Constants.KEY_ID, movie.id)
        i.putExtra(Constants.KEY_BACKDROP_PATH, movie.backdropPath)
        i.putExtra(Constants.KEY_POSTER_PATH, movie.posterPath)
        i.putExtra(Constants.KEY_RELEASE_DATE, movie.releaseDate)
        i.putExtra(Constants.KEY_TITLE, movie.title)
        i.putExtra(Constants.KEY_VOTE_AVERAGE, movie.voteAverage)
        startActivity(i)
    }

    private fun setUpAdapter(adapter: MoviesListAdapter, id: Int){
        findViewById<RecyclerView>(id).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
            this.addOnScrollListener(object : RecyclerView.OnScrollListener()
            {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
                {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        presenter.fetchMoreMoviesData(adapter.getDataType(), adapter.getNextPage())
                    }
                }
            })
        }
    }

    override fun showProgressBar() {
        findViewById<ProgressBar>(R.id.pb_movies).visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.pb_movies).visibility = View.GONE
    }

    override fun hideMovies() {
        println("---hideMovies")
    }

    override fun showMovies() {
        println("---showMovies")
    }

    override fun onFetchMoviesSuccess(type: Int, movies: List<ResultsItemMovies>){
        if (type == Constants.TYPE_POPULAR){
            this.popularMoviesAdapter.setMovieList(movies)
            this.popularMoviesAdapter.notifyDataSetChanged()
        }else{
            this.topRatedMoviesAdapter.setMovieList(movies)
            this.topRatedMoviesAdapter.notifyDataSetChanged()
        }
    }

    override fun onFetchMovieDetailSuccess(response: MovieDetailsResponse) {
        TODO("Not yet implemented")
    }

    override fun onFetchMoreMoviesSuccess(type: Int, movies: List<ResultsItemMovies>) {
        if (type == Constants.TYPE_POPULAR){
            this.popularMoviesAdapter.addMoreData(movies)
            this.popularMoviesAdapter.notifyDataSetChanged()
        }else{
            this.topRatedMoviesAdapter.addMoreData(movies)
            this.topRatedMoviesAdapter.notifyDataSetChanged()
        }
    }

    override fun showDataFetchError() {
        println("---showDataERROR")
    }

    override fun showMoreDataFetchError(type: Int) {
        if (type == Constants.TYPE_TOP_RATED) {
            topRatedMoviesAdapter.pageBack()
        }else{
            popularMoviesAdapter.pageBack()
        }
    }

    override fun reloadData() {
        println("---reloadData")
    }



}