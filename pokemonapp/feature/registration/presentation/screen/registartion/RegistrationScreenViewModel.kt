package com.kotlin.pokemonapp.feature.registration.presentation.screen.registartion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.pokemonapp.feature.registration.domain.model.UserModel
import com.kotlin.pokemonapp.feature.registration.domain.repository.UserRepository
import com.kotlin.pokemonapp.feature.registration.domain.usecase.RegistrationFormValidationError
import com.kotlin.pokemonapp.feature.registration.domain.usecase.ValidateRegistrationFormUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

internal class RegistrationScreenViewModel(
    private val userRepository: UserRepository,
    private val validateRegistrationFormUseCase: ValidateRegistrationFormUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<RegistrationScreenState>(RegistrationScreenState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _dataState = MutableStateFlow(RegistrationScreenDataState())
    val dataState = _dataState.asStateFlow()

    fun onRegistrationEvent(registrationScreenEvent: RegistrationScreenEvent) {
        when (registrationScreenEvent) {
            is RegistrationScreenEvent.EmailChanged -> updateEmail(registrationScreenEvent.email)
            is RegistrationScreenEvent.NameChanged -> updateName(registrationScreenEvent.name)
            is RegistrationScreenEvent.PasswordAgainChanged -> TODO()
            is RegistrationScreenEvent.PasswordChanged -> TODO()
            is RegistrationScreenEvent.SubmitClicked -> {
                validateInput()
            }

            RegistrationScreenEvent.ErrorDialogDismiss -> {
                _dataState.update { it.copy(errorDialog = null) }
            }
        }

        checkButtonEnabled()
    }


    private fun updateEmail(email: String) {
        _dataState.update { it.copy(email = email.trim(), emailError = false) }
    }

    private fun updateName(name: String) {
        _dataState.update { it.copy(name = name.trim(), nameError = false) }
    }

    /**
     * This function is called after every event (`SignUpScreenEvent`) to check if the submit button should be enabled
     */
    private fun checkButtonEnabled() {
        val submitEnabled = dataState.value.let { dataState ->
            dataState.name.isNotEmpty() && dataState.email.isNotEmpty()
        }
        _dataState.value = dataState.value.copy(submitButtonEnabled = submitEnabled)
    }

    /**
     * This function is called after user clicked on submit button
     */
    private fun validateInput() {
        viewModelScope.launch(Dispatchers.Default) {
            _dataState.value = dataState.value.copy(
                emailError = false,
                nameError = false,
                passwordError = false,
            )
            val state = dataState.value
            val errors = validateRegistrationFormUseCase.validate(
                email = state.email,
                name = state.name,
                password = state.password,
                passwordAgain = state.passwordAgain,
            )

            errors.forEach { error ->
                when (error) {
                    is RegistrationFormValidationError.EmailInvalid -> _dataState.value =
                        dataState.value.copy(emailError = true)

                    is RegistrationFormValidationError.NameInvalid -> _dataState.value =
                        dataState.value.copy(nameError = true)

                    is RegistrationFormValidationError.PasswordsDoNotMatch -> _dataState.value =
                        dataState.value.copy(passwordError = true)
                }
            }

            if (errors.isEmpty()) {
                addUser()
            }
        }
    }

    private fun addUser() {
        viewModelScope.launch {
            try {
                userRepository.addUser(
                    UserModel(
                        id = Random.nextInt(),
                        name = dataState.value.name,
                        age = 1,
                    )
                )
                _uiState.value = RegistrationScreenState.Success
            } catch (e: Exception) {
                _dataState.update {
                    it.copy(errorDialog = e.localizedMessage)
                }
            }
        }
    }
}