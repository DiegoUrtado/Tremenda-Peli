package com.diegourtado.tremendapeli.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.data.remote.ResultsItemTvShows
import com.diegourtado.tremendapeli.utils.Constants

class ListAdapterTvShows constructor(dataType: Int, private val clickListener: (ResultsItemTvShows) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = mutableListOf<ResultsItemTvShows>()
    private var listOfPopularTvShows = mutableListOf<ResultsItemTvShows>()
    private var listOfTopRatedTvShows = mutableListOf<ResultsItemTvShows>()

    private var currentPagePopular = 1
    private var currentPageTopRated = 1
    private var dataType = Constants.TYPE_TV_SHOWS_POPULAR

    init {
        this.dataType = dataType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_tv_show, parent, false))
    }

    fun getNextPage(): Int {
        when (this.dataType) {
            Constants.TYPE_TV_SHOWS_POPULAR -> return getNextPagePopular()
            Constants.TYPE_TV_SHOWS_TOP_RATED -> return getNextPageTopRated()
        }
        return getNextPagePopular()
    }

    fun pageBack() {
        when (this.dataType) {
            Constants.TYPE_TV_SHOWS_POPULAR -> pageBackPopular()
            Constants.TYPE_TV_SHOWS_TOP_RATED -> pageBackTopRated()
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

    fun setDataType(dataType: Int){
        this.dataType = dataType
    }
    fun getDataType(): Int {
        return this.dataType
    }

    fun changeList(){
        when (this.dataType) {
            Constants.TYPE_TV_SHOWS_POPULAR -> list = listOfPopularTvShows
            Constants.TYPE_TV_SHOWS_TOP_RATED -> list = listOfTopRatedTvShows
        }
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val tvShowsViewHolder = viewHolder as ListViewHolder
        viewHolder.itemView.setOnClickListener{
            clickListener(list[position])
        }
        tvShowsViewHolder.bindView(list[position])
    }

    fun setList(list: List<ResultsItemTvShows>) {
        if(this.dataType == Constants.TYPE_TV_SHOWS_POPULAR){
            listOfPopularTvShows = list.toMutableList()
        }else{
            listOfTopRatedTvShows = list.toMutableList()
        }
        changeList()
    }

    fun addMoreData(list: List<ResultsItemTvShows>) {
        if(this.dataType == Constants.TYPE_TV_SHOWS_POPULAR){
            this.listOfPopularTvShows.addAll(list)
        }else{
            this.listOfTopRatedTvShows.addAll(list)
        }
        changeList()
    }

}