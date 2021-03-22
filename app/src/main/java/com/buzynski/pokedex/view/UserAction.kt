package com.buzynski.pokedex.view

interface UserAction {
    fun onItemCellClicked(pokemonName: String, pokemonId: Int)
}