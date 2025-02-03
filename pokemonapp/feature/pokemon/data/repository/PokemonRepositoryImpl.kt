package com.kotlin.pokemonapp.feature.pokemon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kotlin.pokemonapp.feature.pokemon.data.datasource.PokemonPagingDataSource
import com.kotlin.pokemonapp.feature.pokemon.data.datasource.remote.PokemonAPIService
import com.kotlin.pokemonapp.feature.pokemon.data.datasource.remote.entity.toModel
import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonDetailModel
import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonModel
import com.kotlin.pokemonapp.feature.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

internal class PokemonRepositoryImpl(
    private val remoteDataSource: PokemonAPIService,
    private val pagingDataSource: PokemonPagingDataSource,
) : PokemonRepository {
    override suspend fun getPokemon(): List<PokemonModel> {
        return remoteDataSource.getPokemon().results.map { entity -> entity.toModel() }
    }

    override suspend fun getPokemonDetail(id: String): PokemonDetailModel {
        return remoteDataSource.getPokemonDetail(id).let {
            PokemonDetailModel(
                name = it.name,
                baseExperience = it.baseExperience,
                height = it.height,
                weight = it.weight,
            )
        }
    }

    override suspend fun getPokemonPaging(): Flow<PagingData<PokemonModel>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                prefetchDistance = 1,
                enablePlaceholders = false
            )
        ) {
            pagingDataSource
        }.flow
    }
}
