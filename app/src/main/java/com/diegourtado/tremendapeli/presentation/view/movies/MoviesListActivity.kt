package com.diegourtado.tremendapeli.presentation.view.movies

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
import com.diegourtado.tremendapeli.presentation.view.DetailActivity
import com.diegourtado.tremendapeli.presentation.view.adapter.ListAdapterMovies
import com.diegourtado.tremendapeli.presentation.view.adapter.ListAdapterTvShows
import com.diegourtado.tremendapeli.utils.Animations
import com.diegourtado.tremendapeli.utils.Constants
import com.diegourtado.tremendapeli.utils.Util

class MoviesListActivity : BaseActivity<ItemPresenter>() , Contract.View {

    lateinit var adapter: ListAdapterMovies

    private lateinit var cardPopular: CardView
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
        setContentView(R.layout.activity_list)
        findViewById<TextView>(R.id.title).setText(R.string.title_movies)
        initRecyclerView()
        initButtons()
        setupSearch()
        fetchData()
    }

    private fun fetchData(){
        presenter.fetchDataMovies(true)
        presenter.fetchDataMovies(false)
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
        presenter.searchMovies(query)
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
        this.adapter = ListAdapterMovies() { movie ->
            showMovie(movie)
        }
        adapter.setPopularSelected(false)
    }

    private fun showMovie(movie: ResultsItemMovies){
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra( Constants.KEY_ID, movie.id)
        i.putExtra(Constants.KEY_BACKDROP_PATH, movie.backdropPath)
        i.putExtra(Constants.KEY_POSTER_PATH, movie.posterPath)
        i.putExtra(Constants.KEY_RELEASE_DATE, movie.releaseDate)
        i.putExtra(Constants.KEY_TITLE, movie.title)
        i.putExtra(Constants.KEY_VOTE_AVERAGE, movie.voteAverage)
        startActivity(i)
    }

    private fun setUpAdapter(adapter: ListAdapterMovies, id: Int){
        findViewById<RecyclerView>(id).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
            this.addOnScrollListener(object : RecyclerView.OnScrollListener()
            {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
                {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        presenter.fetchMoreDataMovies(adapter.isPopularSelected(), adapter.getNextPage())
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

    override fun onFetchMoviesSuccess(movies: List<ResultsItemMovies>) {
        adapter.setList(movies)
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

    override fun showDataFetchError() {
        println("---showDataERROR")
    }

    override fun onFetchMoreDataMoviesSuccess(isPopular: Boolean, movies: List<ResultsItemMovies>) {
        this.adapter.addMoreData(movies)
        this.adapter.notifyDataSetChanged()
    }

    override fun onFetchMoreDataTvShowsSuccess(isPopular: Boolean, movies: List<ResultsItemTvShows>) {
        TODO("Not yet implemented")
    }

    override fun showMoreDataFetchError(type: Int) {
        adapter.pageBack()
    }

    override fun onSearchMovieSuccess(movies: List<ResultsItemMovies>) {
        adapter.setList(movies)
        Animations.fadeIn(findViewById(R.id.list_container))
    }

    override fun onSearchTvShowsSuccess(movies: List<ResultsItemTvShows>) {
        TODO("Not yet implemented")
    }

}