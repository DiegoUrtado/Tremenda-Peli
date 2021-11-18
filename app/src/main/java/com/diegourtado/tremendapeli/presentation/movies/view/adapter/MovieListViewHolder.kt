package com.diegourtado.tremendapeli.presentation.movies.view.adapter

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.utils.Constants
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(movie: ResultsItemMovies) {
        print(movie.title)
        itemView.tv_title.text = movie.title
        Glide.with(itemView.context).load(Constants.URL_IMAGES + movie.posterPath).into(itemView.iv_movie)
    }

}