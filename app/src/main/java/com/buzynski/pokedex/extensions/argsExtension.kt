package com.buzynski.pokedex.extensions

import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtil {
    val gson: Gson = GsonBuilder().create()
}

fun <T> Bundle.getJsonExtra(name: String, `class`: Class<T>): T? {
    val stringExtra = getString(name)
    if (stringExtra != null) {
        return GsonUtil.gson.fromJson<T>(stringExtra, `class`)
    }

    return null
}

fun Bundle.putExtraJson(name: String, src: Any?) {
    putString(name, GsonUtil.gson.toJson(src))
}