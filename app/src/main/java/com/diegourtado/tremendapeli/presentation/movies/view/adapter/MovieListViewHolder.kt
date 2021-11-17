package com.diegourtado.tremendapeli.presentation.movies.view.adapter

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var itemClick: ((String) -> Unit)? = null

    var URL_IMAGES = "https://image.tmdb.org/t/p/w185/"

    fun bindView(movie: ResultsItemMovies) {
        print(movie.title)
        itemView.tv_title.text = movie.title
        Glide.with(itemView.context).load(URL_IMAGES + movie.posterPath).into(itemView.iv_movie)
        itemView?.setOnClickListener {
            itemClick?.invoke(movie.title!!)
        }
    }

}