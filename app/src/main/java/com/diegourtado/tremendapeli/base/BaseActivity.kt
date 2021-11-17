package com.diegourtado.tremendapeli.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity <Presenter : BasePresenter> : AppCompatActivity() {

    protected lateinit var presenter : Presenter

    protected abstract fun createPresenter(context: Context) : Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter(this)
        presenter.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        presenter.onSavedInstanceState(outState)
    }


}