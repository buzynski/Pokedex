package com.buzynski.pokedex.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.buzynski.pokedex.api.model.Characteristic

object PokemonPreviewBinding {

    @BindingAdapter(
        "characteristic"
    )
    @JvmStatic
    fun TextView.setPokemonCharacteristic(
        descriptions: List<Characteristic.Description>?
    ) {
        descriptions?.let { it ->
            this.text = it.firstOrNull { description -> description.language.name == "en" }?.description ?: "No characteristics."
        }
    }
}