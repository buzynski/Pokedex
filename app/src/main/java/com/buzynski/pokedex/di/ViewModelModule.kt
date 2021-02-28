package com.buzynski.pokedex.di

import com.buzynski.pokedex.viewmodel.MainViewViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewViewModel(get()) }

}