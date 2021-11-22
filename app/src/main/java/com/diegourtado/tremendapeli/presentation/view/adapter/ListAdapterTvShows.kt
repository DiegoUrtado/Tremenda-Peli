package com.diegourtado.tremendapeli.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.data.remote.ResultsItemTvShows

class ListAdapterTvShows constructor(private val clickListener: (ResultsItemTvShows) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfPopularTvShows = mutableListOf<ResultsItemTvShows>()
    private var listOfTopRatedTvShows = mutableListOf<ResultsItemTvShows>()
    private var listSearchTvShows = mutableListOf<ResultsItemTvShows>()

    private var currentPagePopular = 1
    private var currentPageTopRated = 1
    private var currentPageSearch = 1
    private var popularSelected = false
    private var searching = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_tv_show, parent, false))
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

    private fun getList() : List<ResultsItemTvShows> {
        return if (isSearching()){
            listSearchTvShows
        }else{
            if (isPopularSelected()) {
                listOfPopularTvShows
            }else{
                listOfTopRatedTvShows
            }
        }
    }

    override fun getItemCount(): Int = getList().size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val tvShowsViewHolder = viewHolder as ListViewHolder
        viewHolder.itemView.setOnClickListener{
            clickListener(getList()[position])
        }
        tvShowsViewHolder.bindView(getList()[position])
    }

    fun setList(list: List<ResultsItemTvShows>) {
        if (isSearching()){
            listSearchTvShows = list.toMutableList()
        }else{
            if(isPopularSelected()){
                listOfPopularTvShows = list.toMutableList()
            }else{
                listOfTopRatedTvShows = list.toMutableList()
            }
        }
        this.notifyDataSetChanged()
    }

    fun addMoreData(list: List<ResultsItemTvShows>) {
        if (isSearching()){
            listSearchTvShows.addAll(list)
        }else{
            if(isPopularSelected()){
                this.listOfPopularTvShows.addAll(list)
            }else{
                this.listOfTopRatedTvShows.addAll(list)
            }
        }
        this.notifyDataSetChanged()
    }

}