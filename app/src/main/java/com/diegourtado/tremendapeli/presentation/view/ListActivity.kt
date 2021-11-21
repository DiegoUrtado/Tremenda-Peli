package com.diegourtado.tremendapeli.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.base.BaseActivity
import com.diegourtado.tremendapeli.data.remote.*
import com.diegourtado.tremendapeli.interactor.Interactor
import com.diegourtado.tremendapeli.presentation.Contract
import com.diegourtado.tremendapeli.presentation.presenter.ItemPresenter
import com.diegourtado.tremendapeli.presentation.view.adapter.ListAdapterMovies
import com.diegourtado.tremendapeli.presentation.view.adapter.ListAdapterTvShows
import com.diegourtado.tremendapeli.utils.Animations
import com.diegourtado.tremendapeli.utils.Constants
import com.diegourtado.tremendapeli.utils.Util

class ListActivity : BaseActivity<ItemPresenter>() , Contract.View {

    private var dataType = -1

    private var popularSelected = true

    lateinit var adapterMovies: ListAdapterMovies
    lateinit var adapterTvShows: ListAdapterTvShows

    lateinit var cardPopular: CardView
    lateinit var tvPopular: TextView

    lateinit var cardTopRated: CardView
    lateinit var tvTopRated: TextView

    lateinit var etSearch: SearchView

    override fun createPresenter(context: Context) : ItemPresenter {
        return ItemPresenter(this, Interactor())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Util.hideStatusBar(window)
        dataType = intent.getIntExtra(Constants.KEY_TYPE, Constants.TYPE_MOVIES_POPULAR)
        setContentView(R.layout.activity_list)

        initRecyclerView()
        initButtons()
        setupSearch()
        fetchData()
    }

    private fun fetchData(){
        if (dataType == Constants.TYPE_MOVIES_POPULAR || dataType == Constants.TYPE_MOVIES_TOP_RATED){
            presenter.fetchData(Constants.TYPE_MOVIES_POPULAR)
            presenter.fetchData(Constants.TYPE_MOVIES_TOP_RATED)
        }else{
            presenter.fetchData(Constants.TYPE_TV_SHOWS_POPULAR)
            presenter.fetchData(Constants.TYPE_TV_SHOWS_TOP_RATED)
        }
    }

    private fun setupSearch(){
        etSearch = findViewById(R.id.et_search)
        etSearch.setOnCloseListener {
            Animations.fadeIn(findViewById(R.id.buttons_container))
            false
        }
        etSearch.setOnSearchClickListener{
            clickSearch()
        }

    }



    private fun clickSearch(){

        Animations.fadeOut(findViewById(R.id.buttons_container))
    }

    private fun initButtons(){
        cardPopular = findViewById(R.id.card_popular)
        cardTopRated = findViewById(R.id.card_top_rated)
        tvPopular = findViewById(R.id.tv_popular)
        tvTopRated = findViewById(R.id.tv_top_rated)
        updateButtons()
    }

    fun clickTopRated(view: View) {
        updateSelection(false)
    }

    fun clickPopular(view: View) {
        updateSelection(true)
    }

    private fun updateSelection(popularSelected: Boolean){
        this.popularSelected = popularSelected
        updateButtons()
        updateAdapter()
    }

    private fun updateAdapter(){
        adapter.setDataType(this.dataType)
        adapter.changeList()
    }

    private fun updateButtons(){
        if (popularSelected) {
            cardPopular.setCardBackgroundColor(resources.getColor(R.color.colorPrimary, null))
            cardTopRated.setCardBackgroundColor(resources.getColor(R.color.light_grey, null))
            cardPopular.cardElevation = 10f
            cardTopRated.cardElevation = 0f
            tvPopular.setTextColor(resources.getColor(R.color.white, null))
            tvTopRated.setTextColor(resources.getColor(R.color.grey, null))
        }else{
            cardPopular.setCardBackgroundColor(resources.getColor(R.color.light_grey, null))
            cardTopRated.setCardBackgroundColor(resources.getColor(R.color.colorPrimary, null))
            cardPopular.cardElevation = 0f
            cardTopRated.cardElevation = 10f
            tvPopular.setTextColor(resources.getColor(R.color.grey, null))
            tvTopRated.setTextColor(resources.getColor(R.color.white, null))
        }
    }

    private fun initRecyclerView() {

        createAdapter()

        if (dataType == Constants.TYPE_MOVIES_POPULAR || dataType == Constants.TYPE_MOVIES_TOP_RATED){
            setUpAdapter(this.adapterMovies, R.id.recycler_view)
        } else {
            setUpAdapter(this.adapterTvShows, R.id.recycler_view)
        }
    }

    private fun createAdapter(){
        if (this.dataType == Constants.TYPE_MOVIES_POPULAR || this.dataType == Constants.TYPE_MOVIES_TOP_RATED){
            this.adapterMovies = ListAdapterMovies(Constants.TYPE_MOVIES_TOP_RATED) { movie ->
                showMovie(movie)
            }
        }else{
            this.adapterTvShows = ListAdapterTvShows(Constants.TYPE_MOVIES_TOP_RATED) { tvShow ->
                showTvShow(tvShow)
            }
        }
    }

    private fun showMovie(movie: ResultsItemMovies){
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra( Constants.KEY_TYPE, this.dataType)
        i.putExtra( Constants.KEY_ID, movie.id)
        i.putExtra(Constants.KEY_BACKDROP_PATH, movie.backdropPath)
        i.putExtra(Constants.KEY_POSTER_PATH, movie.posterPath)
        i.putExtra(Constants.KEY_RELEASE_DATE, movie.releaseDate)
        i.putExtra(Constants.KEY_TITLE, movie.title)
        i.putExtra(Constants.KEY_VOTE_AVERAGE, movie.voteAverage)
        startActivity(i)
    }


    private fun showTvShow(tvShow: ResultsItemTvShows){
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra( Constants.KEY_TYPE, this.dataType)
        i.putExtra( Constants.KEY_ID, tvShow.id)
        i.putExtra(Constants.KEY_BACKDROP_PATH, tvShow.backdropPath)
        i.putExtra(Constants.KEY_POSTER_PATH, tvShow.posterPath)
        i.putExtra(Constants.KEY_RELEASE_DATE, tvShow.firstAirDate)
        i.putExtra(Constants.KEY_TITLE, tvShow.name)
        i.putExtra(Constants.KEY_VOTE_AVERAGE, tvShow.voteAverage)
        startActivity(i)
    }

    private fun setUpAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, id: Int){
        findViewById<RecyclerView>(id).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
            this.addOnScrollListener(object : RecyclerView.OnScrollListener()
            {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
                {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        presenter.fetchMoreData(dataType, getAdapter().getNextPage())
                    }
                }
            })
        }
    }

    private fun getAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder> {
        if (this.dataType == Constants.TYPE_MOVIES_POPULAR || this.dataType == Constants.TYPE_MOVIES_TOP_RATED){
            return this.adapterMovies
        }else{
            return this.adapterTvShows
        }
    }

    override fun showProgressBar() {
        findViewById<ProgressBar>(R.id.progress).visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.progress).visibility = View.GONE
    }

    override fun hideList() {
        findViewById<RecyclerView>(R.id.recycler_view).visibility = View.GONE
    }

    override fun showList() {
        findViewById<RecyclerView>(R.id.recycler_view).visibility = View.VISIBLE
    }

    override fun onFetchMoviesSuccess(movies: List<ResultsItemMovies>) {
        this.adapter.setMovieList(movies)
    }

    override fun onFetchTvShowsSuccess(movies: List<ResultsItemTvShows>) {
        TODO("Not yet implemented")
    }

    override fun onFetchDetailMovieSuccess(response: MovieResultItem) {
        TODO("Not yet implemented")
    }

    override fun onFetchDetailTvShowSuccess(response: TvShowsSingleResponse) {
        TODO("Not yet implemented")
    }

    override fun onFetchMoreDataSuccess(type: Int, movies: List<ResultsItemMovies>) {
        this.adapter.addMoreData(movies)
        this.adapter.notifyDataSetChanged()
    }

    override fun showDataFetchError() {
        println("---showDataERROR")
    }

    override fun showMoreDataFetchError(type: Int) {
        adapter.pageBack()
    }

}