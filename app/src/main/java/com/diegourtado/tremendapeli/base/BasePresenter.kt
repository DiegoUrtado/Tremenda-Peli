package com.diegourtado.tremendapeli.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper

abstract class BasePresenter protected constructor(){

    @CallSuper
    internal fun onCreate(savedInstanceState: Bundle?){

    }

    @CallSuper
    internal fun onResume(){
    }

    @CallSuper
    internal fun onPause(){
    }

    @CallSuper
    internal fun onSavedInstanceState(outState : Bundle){

    }

    @CallSuper
    internal fun onDestroy(){
    }

    @CallSuper
    internal fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?){
    }

    @CallSuper
    internal fun onRequestPermissionsResult(requestCode : Int, permission : Array<String>, grantResults: IntArray){
    }

}