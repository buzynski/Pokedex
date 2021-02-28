package com.buzynski.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.buzynski.pokedex.api.Resource
import com.buzynski.pokedex.api.model.Characteristic
import com.buzynski.pokedex.api.repository.PokedexRepository
import com.buzynski.pokedex.base.BaseViewModel
import com.buzynski.pokedex.helpers.Event
import kotlinx.coroutines.launch

class PokemonPreviewViewModel(private val pokedexRepository: PokedexRepository): BaseViewModel() {

    private val _pokemonCharacteristic = MediatorLiveData<Event<Characteristic>>()
    val pokemonCharacteristic: LiveData<Event<Characteristic>> get() = _pokemonCharacteristic

    // ---

    fun fetchData(pokemonId: Int) {
        fetchPokemonCharacteristic(pokemonId)
    }

    // ---

    private fun fetchPokemonCharacteristic(pokemonId: Int) = viewModelScope.launch {
        setLoadingState(true)

        val response = pokedexRepository.getPokemonCharacteristic(pokemonId)
        when (response.status) {
            Resource.Status.SUCCESS -> {
                response.data?.let {
                    _pokemonCharacteristic.value = Event(it)
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