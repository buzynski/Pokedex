package com.buzynski.pokedex.api.model

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<Result>
) {
    data class Result(
        val name: String,
        val url: String
    )
}