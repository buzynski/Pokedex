package com.buzynski.pokedex.extensions

import com.buzynski.pokedex.BuildConfig

fun Int.getBackImageUrlFromId(): String {
    return "${BuildConfig.API_BACK_IMG_URL}${this + 1}.gif"
}

fun Int.getFrontImageUrlFromId(): String {
    return "${BuildConfig.API_FRONT_IMG_URL}${this}.gif"
}