package com.diegourtado.tremendapeli.data.cache

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


class ImageCacheImpl constructor(context: Context): ImageCache {

    var sharedPrefs : SharedPreferences? = null

    init {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun saveUrl(url: String) {
        sharedPrefs?.edit()?.putString("url", url)?.apply()
    }
}