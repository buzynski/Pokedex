package com.buzynski.pokedex.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val directions: NavDirections): NavigationCommand()
    data class BackWithArgs<T>(val key: String, val data: T): NavigationCommand()
    object Back: NavigationCommand()
}