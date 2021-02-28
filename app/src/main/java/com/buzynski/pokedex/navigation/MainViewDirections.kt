package com.buzynski.pokedex.navigation

import android.os.Bundle
import androidx.navigation.NavDirections
import com.buzynski.pokedex.R
import com.buzynski.pokedex.extensions.putExtraJson

class MainViewDirections private constructor() {
    private data class ActionMainViewFragmentToPreviewFragment(val pokemonName: String, val pokemonId: Int): NavDirections {
        override fun getActionId(): Int = R.id.action_mainFragment_to_pokemonPreviewFragment

        override fun getArguments(): Bundle {
            val result = Bundle()
            result.putExtraJson("pokemonName", pokemonName)
            result.putExtraJson("pokemonId", pokemonId)
            return result
        }
    }

    companion object {
        fun actionMainViewFragmentToPreviewFragment(pokemonName: String, pokemonId: Int): NavDirections =
            ActionMainViewFragmentToPreviewFragment(pokemonName, pokemonId)
    }
}