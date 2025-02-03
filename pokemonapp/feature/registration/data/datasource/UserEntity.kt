package com.kotlin.pokemonapp.feature.registration.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kotlin.pokemonapp.feature.registration.domain.model.UserModel

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int
)

fun UserEntity.toModel(): UserModel = UserModel(
    id = id,
    name = name,
    age = age
)


fun UserModel.toEntity(): UserEntity = UserEntity(
    id = id,
    name = name,
    age = age
)