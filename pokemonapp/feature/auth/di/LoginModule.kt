package com.kotlin.pokemonapp.feature.auth.di

import com.kotlin.pokemonapp.feature.auth.data.datasource.PostManAPIService
import com.kotlin.pokemonapp.feature.auth.data.datasource.ReqresAPIService
import com.kotlin.pokemonapp.feature.auth.domain.BasicAuthRepository
import com.kotlin.pokemonapp.feature.auth.domain.BasicAuthRepositoryImpl
import com.kotlin.pokemonapp.feature.auth.domain.TokenAuthRepository
import com.kotlin.pokemonapp.feature.auth.domain.TokenAuthRepositoryImpl
import com.kotlin.pokemonapp.feature.auth.presentation.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASIC_LOGIN_DEMO_CLIENT = "BASIC_LOGIN_DEMO_CLIENT"
private const val TOKEN_LOGIN_DEMO_CLIENT = "TOKEN_LOGIN_DEMO_CLIENT"
private const val POSTMAN_API_URL = "https://postman-echo.com/"
private const val REQRES_API_URL = "https://reqres.in/api/"

val loginModule = module {
    viewModel {
        LoginViewModel(
            basicAuthRepository = get(),
            tokenAuthRepository = get(),
        )
    }

    single<BasicAuthRepository> {
        BasicAuthRepositoryImpl(
            postmanApi = get(),
        )
    }

    single<TokenAuthRepository> {
        TokenAuthRepositoryImpl(
            remoteDataSource = get(),
        )
    }

    single<Retrofit>(named(BASIC_LOGIN_DEMO_CLIENT)) {
        Retrofit.Builder().baseUrl(POSTMAN_API_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single<Retrofit>(named(TOKEN_LOGIN_DEMO_CLIENT)) {
        Retrofit.Builder().baseUrl(REQRES_API_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single<ReqresAPIService> {
        (get(named(TOKEN_LOGIN_DEMO_CLIENT)) as Retrofit).create(ReqresAPIService::class.java)
    }

    single<PostManAPIService> {
        (get(named(BASIC_LOGIN_DEMO_CLIENT)) as Retrofit).create(PostManAPIService::class.java)
    }
}
