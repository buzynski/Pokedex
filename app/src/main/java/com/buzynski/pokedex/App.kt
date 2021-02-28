package com.buzynski.pokedex

import android.app.Application
import android.content.Context
import com.buzynski.pokedex.di.singleModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    init {
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()

            modules(
                listOf(
                    singleModule
                )
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}