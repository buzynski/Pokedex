package com.buzynski.pokedex.rv.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.buzynski.pokedex.api.model.PokemonList
import com.buzynski.pokedex.databinding.CellMainViewBinding

class MainViewViewHolder(parent: View): RecyclerView.ViewHolder(parent) {

    private val binding = CellMainViewBinding.bind(parent)

    fun bind(imgUrl: String, pokemon: PokemonList.Result) {
        binding.pokemon = pokemon
        binding.imgUrl = imgUrl
    }
}