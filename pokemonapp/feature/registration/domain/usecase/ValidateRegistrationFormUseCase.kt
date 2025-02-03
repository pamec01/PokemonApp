package com.kotlin.pokemonapp.feature.registration.domain.usecase

interface ValidateRegistrationFormUseCase {
    suspend fun validate(
        email: String,
        name: String,
        password: String,
        passwordAgain: String,
    ): List<RegistrationFormValidationError>
}

sealed class RegistrationFormValidationError {
    data class EmailInvalid(val value: String) : RegistrationFormValidationError()
    data class NameInvalid(val value: String) : RegistrationFormValidationError()
    data class PasswordsDoNotMatch(val value: String) : RegistrationFormValidationError()
}
