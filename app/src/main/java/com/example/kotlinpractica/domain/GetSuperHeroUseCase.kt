package com.example.kotlinpractica.domain

import com.example.kotlinpractica.data.SuperHeroRespository
import com.example.kotlinpractica.data.model.SuperHeroModel

class GetSuperHeroUseCase {
    private val respository = SuperHeroRespository()
    suspend operator fun invoke(id: Int): SuperHeroModel? {
        return respository.getSuperHero(id.toString())
    }
}