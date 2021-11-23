package com.diegourtado.tremendapeli.base

import android.os.Bundle
import androidx.annotation.CallSuper

abstract class BasePresenter protected constructor(){

    @CallSuper
    internal fun onCreate(savedInstanceState: Bundle?){

    }

    @CallSuper
    internal fun onSavedInstanceState(outState : Bundle){

    }

}