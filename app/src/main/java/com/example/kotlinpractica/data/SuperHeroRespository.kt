package com.example.kotlinpractica.data

import com.example.kotlinpractica.data.model.SuperHeroModel
import com.example.kotlinpractica.data.network.SuperHeroService

class SuperHeroRespository {

    private val api = SuperHeroService()
    suspend fun getSuperHero(id: String): SuperHeroModel? {
        return api.getSuperHero(id)
    }

}