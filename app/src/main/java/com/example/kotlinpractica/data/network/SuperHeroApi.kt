package com.example.kotlinpractica.data.network

import com.example.kotlinpractica.data.model.SuperHeroModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApi {
    @GET("{id}")
    suspend fun getSuperHero(@Path("id") id: String): Response<SuperHeroModel>
}