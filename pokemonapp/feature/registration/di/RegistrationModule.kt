package com.kotlin.pokemonapp.feature.registration.di

import androidx.room.Room
import com.kotlin.pokemonapp.feature.registration.data.datasource.AppDatabase
import com.kotlin.pokemonapp.feature.registration.data.repository.UserRepositoryImpl
import com.kotlin.pokemonapp.feature.registration.data.usecase.ValidateRegistrationFormUseCaseImpl
import com.kotlin.pokemonapp.feature.registration.data.usecase.validation.CheckMailValidityUseCase
import com.kotlin.pokemonapp.feature.registration.data.usecase.validation.CheckMailValidityUseCaseImpl
import com.kotlin.pokemonapp.feature.registration.data.usecase.validation.CheckNameValidityUseCase
import com.kotlin.pokemonapp.feature.registration.data.usecase.validation.CheckNameValidityUseCaseImpl
import com.kotlin.pokemonapp.feature.registration.domain.repository.UserRepository
import com.kotlin.pokemonapp.feature.registration.domain.usecase.ValidateRegistrationFormUseCase
import com.kotlin.pokemonapp.feature.registration.presentation.screen.allusers.AllUsersScreenViewModel
import com.kotlin.pokemonapp.feature.registration.presentation.screen.registartion.RegistrationScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val registrationModule = module {
    viewModel {
        RegistrationScreenViewModel(
            userRepository = get(),
            validateRegistrationFormUseCase = get(),
        )
    }

    viewModel {
        AllUsersScreenViewModel(
            userRepository = get(),
        )
    }

    factory<ValidateRegistrationFormUseCase> {
        ValidateRegistrationFormUseCaseImpl(
            checkMailValidityUseCase = get(),
            checkNameValidityUseCase = get(),
        )
    }

    factory<CheckMailValidityUseCase> {
        CheckMailValidityUseCaseImpl()
    }
    factory<CheckNameValidityUseCase> {
        CheckNameValidityUseCaseImpl()
    }

    single<UserRepository> {
        UserRepositoryImpl(
            userDbDao = get(),
        )
    }

    // Provide the Room database instance
    single {
        Room.databaseBuilder(
            context = get(),
            klass = AppDatabase::class.java,
            name = "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    // Provide the UserDao instance
    single { get<AppDatabase>().userDao() }
}