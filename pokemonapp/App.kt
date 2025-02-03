package com.kotlin.pokemonapp

import android.app.Application
import com.kotlin.pokemonapp.feature.auth.di.loginModule
import com.kotlin.pokemonapp.feature.counter.di.counterModule
import com.kotlin.pokemonapp.feature.pokemon.di.pokemonModule
import com.kotlin.pokemonapp.feature.registration.di.registrationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(counterModule, registrationModule, pokemonModule, loginModule)
        }
    }
}