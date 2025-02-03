package com.kotlin.pokemonapp.feature.auth.data.datasource

import com.kotlin.pokemonapp.feature.auth.data.entity.ReqresLoginRequestEntity
import com.kotlin.pokemonapp.feature.auth.data.entity.ReqresLoginResponseEntity
import com.kotlin.pokemonapp.feature.auth.data.entity.ReqresPostsResponseEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ReqresAPIService {
    @POST("login")
    suspend fun login(
        @Body body: ReqresLoginRequestEntity,
    ): ReqresLoginResponseEntity

    @GET("posts")
    suspend fun getPosts(
        @Header("Authorization") auth: String,
    ): ReqresPostsResponseEntity
}