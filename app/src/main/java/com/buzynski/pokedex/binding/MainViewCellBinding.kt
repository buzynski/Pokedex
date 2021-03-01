package com.buzynski.pokedex.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.util.*

object MainViewCellBinding {

    @BindingAdapter(
        "pokemonName"
    )
    @JvmStatic
    fun TextView.setPokemonName(
        pokemonName: String?
    ) {
        pokemonName?.let {
            this.text = it.capitalize(Locale.getDefault())
        }
    }

    @BindingAdapter(
        "pokemonUrlImage"
    )
    @JvmStatic
    fun ImageView.setPokemonUrlImage(
        pokemonUrlImage: String?
    ) {
        pokemonUrlImage?.let {
            Glide.with(context)
                .asGif()
                .load(it)
                .into(this)
        }
    }
}