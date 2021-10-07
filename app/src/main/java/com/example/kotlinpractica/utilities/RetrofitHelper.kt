package com.example.kotlinpractica.utilities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com/api.php/6418366364870062/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}