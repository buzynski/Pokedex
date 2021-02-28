package com.buzynski.pokedex.api.clients

import com.buzynski.pokedex.api.BaseClient
import com.buzynski.pokedex.api.service.PokedexService

object ApiClient {

    val pokedexService = BaseClient(PokedexService::class.java).getApiClient()

}