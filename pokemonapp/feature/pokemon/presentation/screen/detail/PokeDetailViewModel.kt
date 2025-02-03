package com.kotlin.pokemonapp.feature.pokemon.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.pokemonapp.feature.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokeDetailViewModel(
    private val id: String,
    private val repository: PokemonRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<PokeDetailScreenState>(PokeDetailScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    fun onScreenLoaded() {
        loadData()
    }

    fun retry() {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val detail = repository.getPokemonDetail(id)
                    _uiState.value = PokeDetailScreenState.Idle(detail)
                } catch (e: Exception) {
                    _uiState.value = PokeDetailScreenState.Error(e.message ?: "Error occurred")
                }
            }
        }
    }
}