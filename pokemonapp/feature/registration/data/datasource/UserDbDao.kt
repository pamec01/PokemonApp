package com.kotlin.pokemonapp.feature.registration.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDbDao {
    @Insert
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE id = :userId")
    fun getUserById(userId: Int): Flow<UserEntity>

    @Delete
    suspend fun deleteUser(user: UserEntity)
}