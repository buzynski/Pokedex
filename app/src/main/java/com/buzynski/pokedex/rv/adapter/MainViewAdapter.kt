package com.buzynski.pokedex.rv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buzynski.pokedex.R
import com.buzynski.pokedex.api.model.PokemonList
import com.buzynski.pokedex.extensions.getImageUrlFromId
import com.buzynski.pokedex.rv.viewHolder.MainViewViewHolder

class MainViewAdapter: RecyclerView.Adapter<MainViewViewHolder>() {

    private var pokemonList: List<PokemonList.Result> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewViewHolder =
        MainViewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_main_view, parent, false))

    override fun onBindViewHolder(holder: MainViewViewHolder, position: Int) {
        holder.bind(position.getImageUrlFromId(), pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size

    // ---

    fun updatePokemonList(pokemonList: List<PokemonList.Result>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }
}