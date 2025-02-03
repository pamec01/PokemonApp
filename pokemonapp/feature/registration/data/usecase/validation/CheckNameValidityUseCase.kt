package com.kotlin.pokemonapp.feature.registration.data.usecase.validation

interface CheckNameValidityUseCase {
    suspend operator fun invoke(name: String): Boolean
}

class CheckNameValidityUseCaseImpl : CheckNameValidityUseCase {
    override suspend fun invoke(name: String): Boolean {
        return name.isNotBlank()
    }
}
