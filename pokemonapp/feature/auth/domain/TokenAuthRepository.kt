package com.kotlin.pokemonapp.feature.auth.domain

import com.kotlin.pokemonapp.feature.auth.data.datasource.ReqresAPIService
import com.kotlin.pokemonapp.feature.auth.data.entity.ReqresLoginRequestEntity

interface TokenAuthRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun getPosts(): List<String>
}

internal class TokenAuthRepositoryImpl(
    private val remoteDataSource: ReqresAPIService,
) : TokenAuthRepository {
    private var token: String? = null

    override suspend fun login(email: String, password: String): Boolean {
        val response = remoteDataSource.login(
            ReqresLoginRequestEntity(
                email = email,
                password = password,
            )
        )
        token = response.token
        return true
    }

    override suspend fun getPosts(): List<String> {
        return remoteDataSource.getPosts(
            auth = "Bearer $token",
        ).data.map { it.name }
    }
}
