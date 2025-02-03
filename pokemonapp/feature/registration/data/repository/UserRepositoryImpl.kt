package com.kotlin.pokemonapp.feature.registration.data.repository

import com.kotlin.pokemonapp.feature.registration.data.datasource.UserDbDao
import com.kotlin.pokemonapp.feature.registration.data.datasource.toEntity
import com.kotlin.pokemonapp.feature.registration.data.datasource.toModel
import com.kotlin.pokemonapp.feature.registration.domain.model.UserModel
import com.kotlin.pokemonapp.feature.registration.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class UserRepositoryImpl(
    private val userDbDao: UserDbDao,
) : UserRepository {
    override suspend fun addUser(userModel: UserModel) {
        userDbDao.insert(userModel.toEntity())
    }

    override fun getAllUsers(): Flow<List<UserModel>> {
        return userDbDao.getAllUsers().map { entities ->
            entities.map { user -> user.toModel() }
        }
    }

    override fun getUser(id: Int): Flow<UserModel> {
        return userDbDao.getUserById(id).map { entity ->
            entity.toModel()
        }
    }
}