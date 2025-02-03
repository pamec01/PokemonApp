package com.kotlin.pokemonapp.feature.auth.presentation

sealed class LoginScreenUIState {
    data object Loading : LoginScreenUIState()
    data object Idle : LoginScreenUIState()
    data class Error(val message: String) : LoginScreenUIState()
    data object Success: LoginScreenUIState()
    data class SuccessToken(val posts: List<String>): LoginScreenUIState()
}

data class LoginScreenDataState(
    val userName: String,
    val password: String,
    val loginType: LoginType,
) {
    companion object {
        val EMPTY = LoginScreenDataState(
            userName = "",
            password = "",
            loginType = LoginType.BASIC,
        )
    }
}

sealed class LoginScreenEvent {
    data class NameChanged(val name: String) : LoginScreenEvent()
    data class PasswordChanged(val password: String) : LoginScreenEvent()
    data object LoginClicked : LoginScreenEvent()
    data class LoginTypeChanged(val loginType: LoginType): LoginScreenEvent()
}

enum class LoginType {
    BASIC, TOKEN,
}
