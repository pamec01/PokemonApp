package com.kotlin.pokemonapp.feature.registration.presentation.screen.registartion

sealed class RegistrationScreenState {
    data object Idle : RegistrationScreenState()
    data object Success : RegistrationScreenState()
}

data class RegistrationScreenDataState(
    val email: String = "",
    val emailError: Boolean = false,
    val name: String = "",
    val nameError: Boolean = false,
    val password: String = "",
    val passwordAgain: String = "",
    val passwordError: Boolean = false,
    val submitButtonEnabled: Boolean = false,
    val errorDialog: String? = null
)

sealed class RegistrationScreenEvent {
    data class EmailChanged(val email: String) : RegistrationScreenEvent()
    data class NameChanged(val name: String) : RegistrationScreenEvent()
    data class PasswordChanged(val password: String) : RegistrationScreenEvent()
    data class PasswordAgainChanged(val passwordAgain: String) : RegistrationScreenEvent()
    data object SubmitClicked : RegistrationScreenEvent()
    data object ErrorDialogDismiss: RegistrationScreenEvent()
}
