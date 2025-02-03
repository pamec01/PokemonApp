package com.kotlin.pokemonapp.feature.pokemon.di

import com.kotlin.pokemonapp.feature.pokemon.data.datasource.PokemonPagingDataSource
import com.kotlin.pokemonapp.feature.pokemon.data.datasource.remote.PokemonAPIService
import com.kotlin.pokemonapp.feature.pokemon.data.repository.PokemonRepositoryImpl
import com.kotlin.pokemonapp.feature.pokemon.domain.repository.PokemonRepository
import com.kotlin.pokemonapp.feature.pokemon.presentation.screen.detail.PokeDetailViewModel
import com.kotlin.pokemonapp.feature.pokemon.presentation.screen.list.PokeListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val POKEMON_API_URL = "https://pokeapi.co/api/"

val pokemonModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            )
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder().baseUrl(POKEMON_API_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single<PokemonAPIService> {
        (get() as Retrofit).create(PokemonAPIService::class.java)
    }

    single<PokemonRepository> {
        PokemonRepositoryImpl(
            remoteDataSource = get(),
            pagingDataSource = get(),
        )
    }

    factory<PokemonPagingDataSource> {
        PokemonPagingDataSource(get())
    }

    viewModel {
        PokeListViewModel(
            pokemonRepository = get(),
        )
    }

    viewModel { param ->
        PokeDetailViewModel(
            id = param.get(),
            repository = get(),
        )
    }
}