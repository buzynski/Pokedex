package com.buzynski.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.buzynski.pokedex.api.Resource
import com.buzynski.pokedex.api.model.PokemonList
import com.buzynski.pokedex.api.repository.PokedexRepository
import com.buzynski.pokedex.base.BaseViewModel
import com.buzynski.pokedex.helpers.Event
import kotlinx.coroutines.launch

class MainViewViewModel(private val pokedexRepository: PokedexRepository): BaseViewModel() {

    private val _pokemonList = MediatorLiveData<Event<PokemonList>>()
    val pokemonList: LiveData<Event<PokemonList>> get() = _pokemonList

    fun fetchData() {
        fetchPokemonList()
    }

    // ---

    private fun fetchPokemonList() = viewModelScope.launch {
        setLoadingState(true)

        val response = pokedexRepository.getPokemonList()
        when (response.status) {
            Resource.Status.SUCCESS -> {
                response.data?.let {
                    _pokemonList.value = Event(it)
                    setLoadingState(false)
                }
            }
            else -> {
                postErrorValue(Exception(response.errorMessage))
                setLoadingState(false)
            }
        }
    }
}