package com.buzynski.pokedex.navigation

import android.os.Bundle
import androidx.navigation.NavArgs
import com.buzynski.pokedex.extensions.getJsonExtra
import com.buzynski.pokedex.extensions.putExtraJson
import java.lang.IllegalArgumentException

class MainViewArgs(val pokemonName: String, val pokemonId: Int) :
    NavArgs {

    fun toBundle(): Bundle {
        val result = Bundle()
        result.putExtraJson("pokemonName", pokemonName)
        result.putExtraJson("pokemonId", pokemonId)
        return result
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle): MainViewArgs {
            bundle.classLoader = MainViewArgs::class.java.classLoader
            val pokemonName: String?
            val pokemonId: Int?
            if (bundle.containsKey("pokemonName")) {
                pokemonName = bundle.getJsonExtra("pokemonName", String::class.java)
                if (pokemonName == null) {
                    throw IllegalArgumentException("Argument \"pokemonName\" is marked as non-null but was passed a null value.")
                }
            } else {
                throw IllegalArgumentException("Required argument \"pokemonName\" is missing and does not have an android:defaultValue")
            }
            if (bundle.containsKey("pokemonId")) {
                pokemonId = bundle.getJsonExtra("pokemonId", Int::class.java)
                if (pokemonId == null) {
                    throw IllegalArgumentException("Argument \"pokemonId\" is marked as non-null but was passed a null value.")
                }
            } else {
                throw IllegalArgumentException("Required argument \"pokemonId\" is missing and does not have an android:defaultValue")
            }

            return MainViewArgs(pokemonName, pokemonId)
        }
    }
}