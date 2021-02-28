package com.buzynski.pokedex.api.repository

import com.buzynski.pokedex.api.Resource
import com.buzynski.pokedex.api.datasource.PokedexDataSource
import com.buzynski.pokedex.api.model.Characteristic
import com.buzynski.pokedex.api.model.PokemonList

class PokedexRepository(private val pokedexDataSource: PokedexDataSource) {

    suspend fun getPokemonList(): Resource<PokemonList> =
        pokedexDataSource.getPokemonList()

    suspend fun getPokemonCharacteristic(id: Int): Resource<Characteristic> =
        pokedexDataSource.getPokemonCharacteristic(id)

}