package com.buzynski.pokedex.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.buzynski.pokedex.extensions.setupProgressDialog
import com.buzynski.pokedex.extensions.setupSnackBar
import com.buzynski.pokedex.navigation.NavigationCommand
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment: Fragment() {

    protected lateinit var navController: NavController

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupProgressDialog(this, getViewModel().loadingState)
        setupSnackBar(this, getViewModel().snackBarError, Snackbar.LENGTH_SHORT)
        observeNavigation(getViewModel())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(requireView())
    }

    // --- NAVIGATION

    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.navigation.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> findNavController().navigate(
                        command.directions,
                        getExtras()
                    )
                    is NavigationCommand.BackWithArgs<*> -> {
                        findNavController().previousBackStackEntry?.savedStateHandle?.set(command.key, command.data)
                        findNavController().navigateUp()
                    }
                    is NavigationCommand.Back -> findNavController().navigateUp()
                }
            }
        })
    }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

    // ---

    abstract fun getViewModel(): BaseViewModel
}