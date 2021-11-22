package com.diegourtado.tremendapeli.presentation.view.adapter

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.data.remote.ResultsItemMovies
import com.diegourtado.tremendapeli.data.remote.ResultsItemTvShows
import com.diegourtado.tremendapeli.utils.Constants
import kotlinx.android.synthetic.main.list_item_movie.view.*

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(movie: ResultsItemMovies) {
        itemView.tv_rating.text = movie.voteAverage.toString()
        itemView.tv_title.text = movie.title
        itemView.tv_date.text = movie.releaseDate
        Glide.with(itemView.context).load(Constants.URL_IMAGES + movie.posterPath).into(itemView.iv_movie)
        itemView.animation = AnimationUtils.loadAnimation(itemView.context, R.anim.rotate)
        itemView.animate()
    }


    fun bindView(tvShow: ResultsItemTvShows) {
        itemView.tv_rating.text = tvShow.voteAverage.toString()
        itemView.tv_title.text = tvShow.name
        itemView.tv_date.text = tvShow.firstAirDate
        Glide.with(itemView.context).load(Constants.URL_IMAGES + tvShow.posterPath).into(itemView.iv_movie)
        itemView.animation = AnimationUtils.loadAnimation(itemView.context, R.anim.rotate)
        itemView.animate()
    }

}