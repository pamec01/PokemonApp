package com.kotlin.pokemonapp.feature.registration.presentation.screen.allusers

import com.kotlin.pokemonapp.feature.registration.domain.model.UserModel

sealed class AllUsersScreenState {
    data object Loading : AllUsersScreenState()
    data class Idle(val users: List<UserModel>): AllUsersScreenState()
}
