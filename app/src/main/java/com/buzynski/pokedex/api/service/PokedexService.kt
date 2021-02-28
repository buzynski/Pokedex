package com.buzynski.pokedex.api.service

import com.buzynski.pokedex.api.model.Characteristic
import com.buzynski.pokedex.api.model.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexService {

    @GET("pokemon")
    suspend fun getPokemonList(): Response<PokemonList>

    @GET("characteristic/{id}")
    suspend fun getPokemonCharacteristic(@Path("id") id: Int): Response<Characteristic>

}