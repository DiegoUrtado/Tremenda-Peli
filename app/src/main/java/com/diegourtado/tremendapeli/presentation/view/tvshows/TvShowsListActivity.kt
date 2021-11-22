package com.diegourtado.tremendapeli.presentation.view.tvshows

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
import com.diegourtado.tremendapeli.presentation.presenter.MoviesPresenter
import com.diegourtado.tremendapeli.presentation.presenter.TvShowsPresenter
import com.diegourtado.tremendapeli.presentation.view.movies.MovieDetailActivity
import com.diegourtado.tremendapeli.presentation.view.adapter.ListAdapterTvShows
import com.diegourtado.tremendapeli.utils.Animations
import com.diegourtado.tremendapeli.utils.Constants
import com.diegourtado.tremendapeli.utils.Util

class TvShowsListActivity : BaseActivity<TvShowsPresenter>() , Contract.TvShowsView {

    lateinit var adapter: ListAdapterTvShows

    lateinit var cardPopular: CardView
    lateinit var tvPopular: TextView

    lateinit var cardTopRated: CardView
    lateinit var tvTopRated: TextView

    lateinit var etSearch: SearchView

    override fun createPresenter(context: Context) : TvShowsPresenter {
        return TvShowsPresenter(this, Interactor())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Util.hideStatusBar(window)
        setContentView(R.layout.activity_list)
        findViewById<TextView>(R.id.title).setText(R.string.title_tv_shows)
        initRecyclerView()
        initButtons()
        setupSearch()
        fetchData()
    }

    private fun fetchData(){
        presenter.fetchDataTvShow(true)
        presenter.fetchDataTvShow(false)
    }

    private fun setupSearch(){
        etSearch = findViewById(R.id.et_search)
        etSearch.setOnCloseListener {
            Animations.fadeIn(findViewById(R.id.list_container))
            false
        }
        etSearch.setOnSearchClickListener{
            clickSearch()
        }
        etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(qString: String): Boolean {
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                etSearch.clearFocus()
                search(query)
                return true
            }
        })
    }

    private fun search(query: String){
        adapter.setSearching(true)
        presenter.searchTvShows(query)
    }

    private fun clickSearch(){
        Animations.fadeOut(findViewById(R.id.list_container))
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
        adapter.setPopularSelected(popularSelected)
        adapter.setSearching(false)
        etSearch.setQuery("", false)
        etSearch.clearFocus()
        etSearch.isIconified = true
        updateButtons()
        updateAdapter()
    }

    private fun updateAdapter(){
        adapter.notifyDataSetChanged()
    }

    private fun updateButtons(){
        if (adapter.isPopularSelected()) {
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
        setUpAdapter(this.adapter, R.id.recycler_view)
    }

    private fun createAdapter(){
        this.adapter = ListAdapterTvShows() { tvShow ->
            showTvShow(tvShow)
        }
        adapter.setPopularSelected(false)
    }

    private fun showTvShow(tvShow: ResultsItemTvShows){
        val i = Intent(this, TvShowDetailActivity::class.java)
        i.putExtra( Constants.KEY_ID, tvShow.id)
        i.putExtra(Constants.KEY_BACKDROP_PATH, tvShow.backdropPath)
        i.putExtra(Constants.KEY_POSTER_PATH, tvShow.posterPath)
        i.putExtra(Constants.KEY_RELEASE_DATE, tvShow.firstAirDate)
        i.putExtra(Constants.KEY_TITLE, tvShow.name)
        i.putExtra(Constants.KEY_VOTE_AVERAGE, tvShow.voteAverage)
        startActivity(i)
    }

    private fun setUpAdapter(adapter: ListAdapterTvShows, id: Int){
        findViewById<RecyclerView>(id).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
            this.addOnScrollListener(object : RecyclerView.OnScrollListener()
            {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
                {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        presenter.fetchMoreDataTvShow(adapter.isPopularSelected(), adapter.getNextPage())
                    }
                }
            })
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

    override fun onFetchTvShowsSuccess(tvShows: List<ResultsItemTvShows>) {
        adapter.setList(tvShows)
    }

    override fun showDataFetchError() {
        Util.showErrorDialog(this)
    }

    override fun onFetchMoreDataTvShowsSuccess(isPopularSelected: Boolean, movies: List<ResultsItemTvShows>) {
        this.adapter.addMoreData(movies)
        this.adapter.notifyDataSetChanged()
    }

    override fun showMoreDataFetchError(type: Int) {
        adapter.pageBack()
    }

    override fun onSearchTvShowsSuccess(tvShows: List<ResultsItemTvShows>) {
        adapter.setList(tvShows)
        Animations.fadeIn(findViewById(R.id.list_container))
    }

}