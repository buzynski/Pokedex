package com.buzynski.pokedex.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.buzynski.pokedex.api.model.PokemonList
import com.buzynski.pokedex.rv.adapter.MainViewAdapter

object MainViewBinding {

    @BindingAdapter(
        "data"
    )
    @JvmStatic
    fun RecyclerView.setData(
        data: List<PokemonList.Result>?
    ) {
        if (this.adapter is MainViewAdapter) {
            with(this.adapter as MainViewAdapter) {
                data?.let {
                    this.updatePokemonList(it)
                }
            }
        }
    }
}