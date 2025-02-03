package com.kotlin.pokemonapp.feature.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.pokemonapp.feature.auth.domain.BasicAuthRepository
import com.kotlin.pokemonapp.feature.auth.domain.TokenAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val basicAuthRepository: BasicAuthRepository,
    private val tokenAuthRepository: TokenAuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginScreenUIState>(LoginScreenUIState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _dataState = MutableStateFlow(LoginScreenDataState.EMPTY)
    val dataState = _dataState.asStateFlow()

    fun onLoginEvent(loginScreenEvent: LoginScreenEvent) {
        when (loginScreenEvent) {
            LoginScreenEvent.LoginClicked -> {
                login()
            }

            is LoginScreenEvent.NameChanged -> _dataState.update { it.copy(userName = loginScreenEvent.name) }
            is LoginScreenEvent.PasswordChanged -> _dataState.update { it.copy(password = loginScreenEvent.password) }
            is LoginScreenEvent.LoginTypeChanged -> _dataState.update { it.copy(loginType = loginScreenEvent.loginType) }
        }
    }

    private fun login() {
        _uiState.value = LoginScreenUIState.Loading

        val method = dataState.value.loginType

        when (method) {
            LoginType.BASIC -> loginBasic()
            LoginType.TOKEN -> loginToken()
        }
    }

    private fun loginBasic() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val username = dataState.value.userName
                val password = dataState.value.password
                val result = basicAuthRepository.authenticate(
                    userName = username,
                    password = password,
                )
                _uiState.value = if (result) {
                    LoginScreenUIState.Success
                } else {
                    LoginScreenUIState.Error("Login failed")
                }
            } catch (e: Exception) {
                _uiState.value = LoginScreenUIState.Error(e.message ?: "Error occurred!")
            }
        }
    }

    private fun loginToken() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val email = dataState.value.userName
                val pass = dataState.value.password
                val result = tokenAuthRepository.login(email, pass)
                _uiState.value = if (result) {
                    val posts = tokenAuthRepository.getPosts()
                    LoginScreenUIState.SuccessToken(posts)
                } else {
                    LoginScreenUIState.Error("Login failed")
                }
            } catch (e: Exception) {
                Log.e("Tag", "", e)
                _uiState.value = LoginScreenUIState.Error(e.message ?: "Error occurred!")
            }
        }
    }

    fun retry() {
        _uiState.value = LoginScreenUIState.Idle
    }
}