package com.diegourtado.tremendapeli.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.utils.Constants


class ListAdapterMovies constructor(private val clickListener: (ResultsItemMovies) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfPopularMovies = mutableListOf<ResultsItemMovies>()
    private var listOfTopRatedMovies = mutableListOf<ResultsItemMovies>()
    private var listSearchMovies = mutableListOf<ResultsItemMovies>()

    private var currentPagePopular = 1
    private var currentPageTopRated = 1
    private var currentPageSearch = 1
    private var popularSelected = false
    private var searching = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false))
    }

    fun getNextPage(): Int {
        return if (isSearching()){
            getNextPageSearch()
        }else{
            if (isPopularSelected()){
                getNextPagePopular()
            }else{
                getNextPageTopRated()
            }
        }
    }

    fun pageBack() {
        return if (isSearching()){
            pageBackSearch()
        }else{
            if (isPopularSelected()){
                pageBackPopular()
            }else{
                pageBackTopRated()
            }
        }
    }

    private fun getNextPagePopular(): Int {
        this.currentPagePopular = this.currentPagePopular + 1
        return this.currentPagePopular
    }

    private fun getNextPageTopRated(): Int {
        this.currentPageTopRated = this.currentPageTopRated + 1
        return this.currentPageTopRated
    }

    private fun pageBackPopular() {
        this.currentPagePopular = this.currentPagePopular - 1
    }

    private fun pageBackTopRated() {
        this.currentPageTopRated = this.currentPageTopRated - 1
    }

    private fun getNextPageSearch(): Int {
        this.currentPageSearch = this.currentPageSearch + 1
        return this.currentPageSearch
    }

    private fun pageBackSearch() {
        this.currentPageSearch = this.currentPageSearch - 1
    }

    fun setSearching(searching: Boolean){
        this.searching = searching
    }
    fun isSearching(): Boolean {
        return this.searching
    }

    fun setPopularSelected(popularSelected: Boolean){
        this.popularSelected = popularSelected
    }
    fun isPopularSelected(): Boolean {
        return this.popularSelected
    }

    private fun getList() : List<ResultsItemMovies> {
        return if (isSearching()){
            listSearchMovies
        }else{
            if (isPopularSelected()) {
                listOfPopularMovies
            }else{
                listOfTopRatedMovies
            }
        }
    }

    override fun getItemCount(): Int = getList().size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val moviesViewHolder = viewHolder as ListViewHolder
        viewHolder.itemView.setOnClickListener{
            clickListener(getList()[position])
        }
        moviesViewHolder.bindView(getList()[position])
    }

    fun setList(list: List<ResultsItemMovies>) {
        if (isSearching()){
            listSearchMovies = list.toMutableList()
        }else{
            if(isPopularSelected()){
                listOfPopularMovies = list.toMutableList()
            }else{
                listOfTopRatedMovies = list.toMutableList()
            }
        }
        this.notifyDataSetChanged()
    }

    fun addMoreData(list: List<ResultsItemMovies>) {
        if (isSearching()){
            listSearchMovies.addAll(list)
        }else{
            if(isPopularSelected()){
                this.listOfPopularMovies.addAll(list)
            }else{
                this.listOfTopRatedMovies.addAll(list)
            }
        }
        this.notifyDataSetChanged()
    }
}