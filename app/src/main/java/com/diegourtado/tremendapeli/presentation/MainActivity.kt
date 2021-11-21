package com.diegourtado.tremendapeli.presentation

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.presentation.view.movies.MoviesListActivity
import com.diegourtado.tremendapeli.presentation.view.tvshows.TvShowsListActivity
import com.diegourtado.tremendapeli.utils.Constants
import com.diegourtado.tremendapeli.utils.Util


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Util.hideStatusBar(window)
        setContentView(R.layout.activity_main)
    }

    fun clickMovies(view: View){
        startActivity(Intent(this, MoviesListActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    fun clickTvShows(view: View){
        startActivity(Intent(this, TvShowsListActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }
}