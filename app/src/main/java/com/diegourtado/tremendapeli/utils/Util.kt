package com.diegourtado.tremendapeli.utils

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.diegourtado.tremendapeli.R


class Util {
    companion object {
        fun hideStatusBar(window: Window){
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }

        fun statusBarColor(color: Int, activity: Activity){
            val window: Window = activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity, color)
        }

        fun showErrorDialog(activity: Activity) {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
            dialog.setContentView(R.layout.dialog_error)
            dialog.setCancelable(false)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            dialog.findViewById<View>(R.id.card_close).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
            dialog.window!!.attributes = lp
        }
    }
}