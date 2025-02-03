package com.kotlin.pokemonapp.feature.registration.data.usecase.validation

import java.util.regex.Pattern

interface CheckMailValidityUseCase {
    suspend operator fun invoke(mail: String): Boolean
}

class CheckMailValidityUseCaseImpl : CheckMailValidityUseCase {
    private val emailRegex = Pattern.compile(
        "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"
    )

    override suspend operator fun invoke(mail: String): Boolean {
        val matcher = emailRegex.matcher(mail)
        return matcher.find()
    }
}

class NotCorrectMailValidityUseCaseImpl : CheckMailValidityUseCase {
    private val emailRegex =
        Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$\n")

    override suspend operator fun invoke(mail: String): Boolean {
        val matcher = emailRegex.matcher(mail)
        return matcher.find()
    }
}