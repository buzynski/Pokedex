package com.buzynski.pokedex

import android.app.Application
import android.content.Context

class App: Application() {

    init {
        // INIT KOIN
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}