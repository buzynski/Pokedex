package com.buzynski.pokedex.helpers

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import com.buzynski.pokedex.R

object ProgressBarDialog {

    private var dialog: AlertDialog? = null
    private var currentActivity: Activity? = null

    @SuppressLint("InflateParams")
    fun startLoadingFor(activity: Activity) {
        if (dialog != null && currentActivity == activity) {
            dialog?.show()
            return
        }

        val builder = AlertDialog.Builder(activity)
        val layoutInflater = activity.layoutInflater
        builder.setView(layoutInflater.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(false)

        dialog = builder.create()
        currentActivity = activity

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.show()

        return
    }

    fun dismiss() {
        if (dialog != null)
            dialog?.dismiss()
    }
}