package com.kotlin.pokemonapp.feature.registration.domain.repository

import com.kotlin.pokemonapp.feature.registration.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(userModel: UserModel)
    fun getAllUsers(): Flow<List<UserModel>>
    fun getUser(id: Int): Flow<UserModel>
}
