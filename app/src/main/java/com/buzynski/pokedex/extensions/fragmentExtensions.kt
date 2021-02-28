package com.buzynski.pokedex.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.buzynski.pokedex.helpers.Event
import com.buzynski.pokedex.helpers.ProgressBarDialog
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(message: String, length: Int) {
    activity?.let { activity ->
        Snackbar.make(
            activity.findViewById(android.R.id.content),
            message,
            length
        ).show()
    }
}

fun Fragment.setupSnackBar(
    lifecycleOwner: LifecycleOwner,
    snackBarEvent: LiveData<Event<String>>,
    timeLength: Int
) {
    snackBarEvent.observe(lifecycleOwner, { event ->
        event.getContentIfNotHandled()?.let { res ->
            context?.let { showSnackBar(res, timeLength) }
        }
    })
}

fun Fragment.showProgressDialog() {
    activity?.let { activity -> ProgressBarDialog.startLoadingFor(activity) }
}

fun Fragment.dismissProgressDialog() {
    activity?.let { ProgressBarDialog.dismiss() }
}

fun Fragment.setupProgressDialog(
    lifecycleOwner: LifecycleOwner,
    progressEvent: LiveData<Event<Boolean>>
) {
    progressEvent.observe(lifecycleOwner, { event ->
        event.getContentIfNotHandled().let { isLoading ->
            if (isLoading != null) {
                isLoading.let {
                    if (it)
                        showProgressDialog()
                    else
                        dismissProgressDialog()
                }
            } else {
                dismissProgressDialog()
            }
        }
    })
}