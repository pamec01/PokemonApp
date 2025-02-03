package com.kotlin.pokemonapp.feature.registration.presentation.screen.allusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.pokemonapp.feature.registration.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllUsersScreenViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<AllUsersScreenState>(AllUsersScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    fun onScreenLoaded() {
        viewModelScope.launch {
            userRepository.getAllUsers().collect {
                _uiState.value = AllUsersScreenState.Idle(it)
            }
        }
    }
}