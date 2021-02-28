package com.buzynski.pokedex.di

import com.buzynski.pokedex.api.datasource.PokedexDataSource
import com.buzynski.pokedex.api.repository.PokedexRepository
import org.koin.dsl.module

val singleModule = module {

    single { PokedexDataSource() }

    factory { PokedexRepository(get()) }

}