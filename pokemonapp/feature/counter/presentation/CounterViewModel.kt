package com.kotlin.pokemonapp.feature.counter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.pokemonapp.feature.counter.domain.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CounterViewModel(private val repository: DataRepository) : ViewModel() {
    private val _counter = MutableStateFlow("Waiting for data...")
    val counter: StateFlow<String> = _counter

    private val _fetchedOnDemand = MutableStateFlow("Waiting for you to click on button")
    val fetchedOnDemand: StateFlow<String> = _fetchedOnDemand

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.fetchDataStream().collect { newCount ->
                    _counter.value = newCount
                }
            }
        }
    }

    fun onButtonClicked() {
        viewModelScope.launch {
            _fetchedOnDemand.value = repository.fetchDataOnce()
        }
    }
}
