package com.buzynski.pokedex.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.buzynski.pokedex.extensions.setupProgressDialog
import com.buzynski.pokedex.extensions.setupSnackBar
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupProgressDialog(this, getViewModel().loadingState)
        setupSnackBar(this, getViewModel().snackBarError, Snackbar.LENGTH_SHORT)
    }

    // ---

    abstract fun getViewModel(): BaseViewModel
}