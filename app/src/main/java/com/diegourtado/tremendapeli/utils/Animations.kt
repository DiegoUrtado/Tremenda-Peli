package com.diegourtado.tremendapeli.utils

import android.view.View
import android.view.animation.*

class Animations {

    companion object {

        fun fadeIn(view: View){
            val fadeIn = AlphaAnimation(0f, 1f)
            fadeIn.interpolator = DecelerateInterpolator()
            fadeIn.startOffset = 0
            fadeIn.duration = 2000
            val animation = AnimationSet(false)
            animation.addAnimation(fadeIn)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    view.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animation?) {

                }

                override fun onAnimationRepeat(animation: Animation?) {

                }

            })
            view.animation = animation
        }

        fun fadeOut(view: View){
            val fadeOut = AlphaAnimation(1f, 0f)
            fadeOut.interpolator = AccelerateInterpolator()
            fadeOut.startOffset = 0
            fadeOut.duration = 1500

            val animation = AnimationSet(false)
            animation.addAnimation(fadeOut)
            view.startAnimation(animation)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    view.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animation?) {

                }

            })
        }
    }
}