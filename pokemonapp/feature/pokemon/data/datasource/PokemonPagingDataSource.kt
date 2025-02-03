package com.kotlin.pokemonapp.feature.pokemon.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kotlin.pokemonapp.feature.pokemon.data.datasource.remote.PokemonAPIService
import com.kotlin.pokemonapp.feature.pokemon.data.datasource.remote.entity.toModel
import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonModel

internal class PokemonPagingDataSource(
    private val remoteService: PokemonAPIService,
) : PagingSource<Int, PokemonModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonModel> {
        return try {
            val currentPage = params.key ?: 1
            val limit = 20
            val response = remoteService.getPokemonPage(offset = currentPage * limit, limit = limit)
            val pokemon = response.results.map { it.toModel() }

            LoadResult.Page(
                data = pokemon,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage < (response.count / 20)) currentPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonModel>): Int? {
        return null
    }
}
