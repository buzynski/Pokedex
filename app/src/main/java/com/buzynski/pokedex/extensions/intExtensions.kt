package com.buzynski.pokedex.extensions

import com.buzynski.pokedex.BuildConfig

fun Int.getImageUrlFromId(): String {
    return "${BuildConfig.API_IMG_URL}${this + 1}.gif"
}