package com.buzynski.pokedex.api.service

import com.buzynski.pokedex.api.model.PokemonList
import retrofit2.Response
import retrofit2.http.GET

interface PokedexService {

    @GET("pokemon")
    suspend fun getPokemonList(): Response<PokemonList>

}