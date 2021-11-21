package com.diegourtado.tremendapeli.presentation

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.diegourtado.tremendapeli.R
import com.diegourtado.tremendapeli.presentation.view.ListActivity
import com.diegourtado.tremendapeli.utils.Constants
import com.diegourtado.tremendapeli.utils.Util


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Util.hideStatusBar(window)
        setContentView(R.layout.activity_main)
    }

    fun clickMovies(view: View){
        launchActivity(Constants.TYPE_MOVIES_POPULAR)
    }

    fun clickTvShows(view: View){
        launchActivity(Constants.TYPE_TV_SHOWS_POPULAR)
    }

    private fun launchActivity(viewType: Int){
        val intent = Intent(this, ListActivity::class.java)
        when (viewType){
            Constants.TYPE_MOVIES_POPULAR -> intent.putExtra(Constants.KEY_TYPE, Constants.TYPE_MOVIES_POPULAR)
            Constants.TYPE_TV_SHOWS_POPULAR -> intent.putExtra(Constants.KEY_TYPE, Constants.TYPE_TV_SHOWS_POPULAR)
            else -> return
        }
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

}