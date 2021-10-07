package com.example.kotlinpractica.data.network

import com.example.kotlinpractica.data.model.SuperHeroModel
import com.example.kotlinpractica.utilities.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class SuperHeroService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getSuperHero(id: String): SuperHeroModel? {
        return withContext(Dispatchers.IO) {
            val response: Response<SuperHeroModel> = retrofit
                .create(SuperHeroApi::class.java)
                .getSuperHero(id)
            response.body()
        }
    }
}