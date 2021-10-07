package com.example.kotlinpractica.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinpractica.data.model.SuperHeroModel
import com.example.kotlinpractica.domain.GetChunkSuperHeroUseCase
import com.google.gson.Gson
import kotlinx.coroutines.launch


class SuperHeroViewModel: ViewModel() {

    val superHeroDetail = MutableLiveData<SuperHeroModel>()

    val getChunkSuperHero = GetChunkSuperHeroUseCase()

    public var chunkSuperHero = 1
    private var totalSuperHeros = ArrayList<SuperHeroModel?>(emptyList());

    val event = MutableLiveData<Int>()

    fun loadSuperHeros() {
        viewModelScope.launch {
            totalSuperHeros = getChunkSuperHero(chunkSuperHero)
            event.postValue(chunkSuperHero)
            chunkSuperHero++
        }
    }

    fun setSuperHeroDetail(data: String) {
        val gson = Gson()
        superHeroDetail.postValue(
            gson.fromJson(data,SuperHeroModel::class.java)
        )
    }

    fun getTotalSuperHeros(): ArrayList<SuperHeroModel?> {
        return totalSuperHeros
    }
}