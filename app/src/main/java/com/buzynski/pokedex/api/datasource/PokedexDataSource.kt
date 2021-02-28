package com.buzynski.pokedex.api.datasource

import com.buzynski.pokedex.api.clients.ApiClient

class PokedexDataSource: BaseDataSource() {

    suspend fun getPokemonList() =
        getResult { ApiClient.pokedexService.getPokemonList() }

    suspend fun getPokemonCharacteristic(id: Int) =
        getResult { ApiClient.pokedexService.getPokemonCharacteristic(id) }

}