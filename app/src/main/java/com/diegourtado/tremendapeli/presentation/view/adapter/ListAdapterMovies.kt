package com.diegourtado.tremendapeli.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.utils.Constants


class ListAdapterMovies constructor(dataType: Int, private val clickListener: (ResultsItemMovies) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = mutableListOf<ResultsItemMovies>()
    private var listOfPopularMovies = mutableListOf<ResultsItemMovies>()
    private var listOfTopRatedMovies = mutableListOf<ResultsItemMovies>()

    private var currentPagePopular = 1
    private var currentPageTopRated = 1
    private var dataType = Constants.TYPE_MOVIES_POPULAR

    init {
        this.dataType = dataType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false))
    }

    fun getNextPage(): Int {
        when (this.dataType) {
            Constants.TYPE_MOVIES_POPULAR -> return getNextPagePopular()
            Constants.TYPE_MOVIES_POPULAR -> return getNextPageTopRated()
        }
        return getNextPagePopular()
    }

    fun pageBack() {
        when (this.dataType) {
            Constants.TYPE_MOVIES_POPULAR -> pageBackPopular()
            Constants.TYPE_MOVIES_TOP_RATED -> pageBackTopRated()
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
            Constants.TYPE_MOVIES_POPULAR -> list = listOfPopularMovies
            Constants.TYPE_MOVIES_TOP_RATED -> list = listOfTopRatedMovies
        }
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val moviesViewHolder = viewHolder as ListViewHolder
        viewHolder.itemView.setOnClickListener{
            clickListener(list[position])
        }
        moviesViewHolder.bindView(list[position])
    }

    fun setList(list: List<ResultsItemMovies>) {
        if(this.dataType == Constants.TYPE_MOVIES_POPULAR){
            listOfPopularMovies = list.toMutableList()
        }else{
            listOfTopRatedMovies = list.toMutableList()
        }
        changeList()
    }

    fun addMoreData(list: List<ResultsItemMovies>) {
        if(this.dataType == Constants.TYPE_MOVIES_POPULAR){
            this.listOfPopularMovies.addAll(list)
        }else{
            this.listOfTopRatedMovies.addAll(list)
        }
        changeList()
    }

}