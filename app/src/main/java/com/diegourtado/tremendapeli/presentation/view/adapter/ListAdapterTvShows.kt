package com.diegourtado.tremendapeli.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.data.remote.ResultsItemTvShows
import com.diegourtado.tremendapeli.utils.Constants

class ListAdapterTvShows constructor(private val clickListener: (ResultsItemTvShows) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfPopularTvShows = mutableListOf<ResultsItemTvShows>()
    private var listOfTopRatedTvShows = mutableListOf<ResultsItemTvShows>()

    private var currentPagePopular = 1
    private var currentPageTopRated = 1
    private var popularSelected = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_tv_show, parent, false))
    }

    fun getNextPage(): Int {
        return if (isPopularSelected()){
            getNextPagePopular()
        }else{
            getNextPageTopRated()
        }
    }

    fun pageBack() {
        return if (isPopularSelected()){
            pageBackPopular()
        }else{
            pageBackTopRated()
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

    fun setPopularSelected(popularSelected: Boolean){
        this.popularSelected = popularSelected
    }
    fun isPopularSelected(): Boolean {
        return this.popularSelected
    }

    fun changeList(){
        setList(getList())
        this.notifyDataSetChanged()
    }

    private fun getList() : List<ResultsItemTvShows> {
        return if (isPopularSelected()) {
            listOfPopularTvShows
        }else{
            listOfTopRatedTvShows
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
        if(isPopularSelected()){
            listOfPopularTvShows = list.toMutableList()
        }else{
            listOfTopRatedTvShows = list.toMutableList()
        }
        this.notifyDataSetChanged()
    }

    fun addMoreData(list: List<ResultsItemTvShows>) {
        if(isPopularSelected()){
            this.listOfPopularTvShows.addAll(list)
        }else{
            this.listOfTopRatedTvShows.addAll(list)
        }
        this.notifyDataSetChanged()
    }

}