package com.diegourtado.tremendapeli.presentation.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies


class MoviesListAdapter constructor(dataType: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfMovies = mutableListOf<ResultsItemMovies>()
    private var currentPage = 1
    private var dataType = 0

    init {
        this.dataType = dataType
    }

    var itemClick: ((String) -> Unit)? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)).apply {
//            itemClick = { movieTitle ->
//                this@MovieListAdapter.itemClick?.invoke(movieTitle)
//            }
//
//            onItemLongPress = { movieModel ->
//                this@MovieListAdapter.onItemLongPress?.invoke(movieModel)
//            }

            itemView.setOnClickListener {
               // this@MovieListAdapter.recyclerViewCallback?.onRecycleViewItemClick(listOfMovies[adapterPosition], adapterPosition)
            }

        }
    }

    fun getNextPage(): Int {
        this.currentPage = this.currentPage + 1
        return this.currentPage
    }

    fun pageBack() {
        this.currentPage = this.currentPage - 1
    }

    fun getDataType(): Int {
        return this.dataType
    }

    override fun getItemCount(): Int = listOfMovies.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as MovieListViewHolder
        movieViewHolder.bindView(listOfMovies[position])
    }

    fun setMovieList(listOfMovies: List<ResultsItemMovies>) {
        this.listOfMovies = listOfMovies.toMutableList()
        notifyDataSetChanged()
    }

    fun addMoreData(listOfMovies: List<ResultsItemMovies>) {
        this.listOfMovies.addAll(listOfMovies)
        notifyDataSetChanged()
    }

    //TODO fun setOnCallbackListener(recyclerViewCallback: RecyclerViewCallback) {
    //    this.recyclerViewCallback = recyclerViewCallback
    //}

}