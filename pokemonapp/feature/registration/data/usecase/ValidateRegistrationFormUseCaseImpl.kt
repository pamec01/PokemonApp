package com.kotlin.pokemonapp.feature.registration.data.usecase

import com.kotlin.pokemonapp.feature.registration.data.usecase.validation.CheckMailValidityUseCase
import com.kotlin.pokemonapp.feature.registration.data.usecase.validation.CheckNameValidityUseCase
import com.kotlin.pokemonapp.feature.registration.domain.usecase.RegistrationFormValidationError
import com.kotlin.pokemonapp.feature.registration.domain.usecase.ValidateRegistrationFormUseCase

internal class ValidateRegistrationFormUseCaseImpl(
    private val checkMailValidityUseCase: CheckMailValidityUseCase,
    private val checkNameValidityUseCase: CheckNameValidityUseCase,
) : ValidateRegistrationFormUseCase {
    override suspend fun validate(
        email: String, name: String, password: String, passwordAgain: String
    ): List<RegistrationFormValidationError> {
        val errors = mutableListOf<RegistrationFormValidationError>()

        if (!checkMailValidityUseCase(email)) {
            errors.add(RegistrationFormValidationError.EmailInvalid(email))
        }

        if (!checkNameValidityUseCase(name)) {
            errors.add(RegistrationFormValidationError.NameInvalid(name))
        }

        // Todo implement other checks

        return errors
    }
}
