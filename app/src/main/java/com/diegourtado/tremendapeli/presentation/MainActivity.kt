package com.diegourtado.tremendapeli.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.presentation.movies.view.MoviesActivity
import com.diegourtado.tremendapeli.presentation.tv_shows.view.TvShowsActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickMovies(view: View){
        startActivity(Intent(this, MoviesActivity::class.java))
    }

    fun clickTvShows(view: View){
        startActivity(Intent(this, TvShowsActivity::class.java))
    }

}