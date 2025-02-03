package com.kotlin.pokemonapp.feature.counter.di

import com.kotlin.pokemonapp.feature.counter.domain.DataRepository
import com.kotlin.pokemonapp.feature.counter.presentation.CounterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val counterModule = module {
    factory {
        DataRepository()
    }

    viewModel {
        CounterViewModel(repository = get())
    }
}