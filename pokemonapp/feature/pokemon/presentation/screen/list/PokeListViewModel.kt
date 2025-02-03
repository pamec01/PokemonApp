package com.kotlin.pokemonapp.feature.pokemon.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonModel
import com.kotlin.pokemonapp.feature.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class PokeListViewModel(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<PokeListScreenUIState>(PokeListScreenUIState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _pagingData = MutableStateFlow<PagingData<PokemonModel>>(PagingData.empty())
    val pagingData = _pagingData.asStateFlow()

    fun onScreenLoad() {
        loadData()
    }

    fun retry() {
        loadData()
    }

    private fun loadData() {
        _uiState.value = PokeListScreenUIState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _uiState.value = PokeListScreenUIState.Idle
                    pokemonRepository.getPokemonPaging()
                        .cachedIn(viewModelScope)
                        .collectLatest {
                            _pagingData.value = it
                        }
                } catch (e: Exception) {
                    _uiState.value = PokeListScreenUIState.Error(e.message ?: "error")
                }
            }
        }
    }
}