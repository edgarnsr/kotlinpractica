package com.example.kotlinpractica.domain

import android.util.Log
import com.example.kotlinpractica.data.SuperHeroRespository
import com.example.kotlinpractica.data.model.SuperHeroModel
import io.reactivex.rxjava3.core.Observable
import java.util.*
import kotlin.collections.ArrayList

class GetChunkSuperHeroUseCase {

    private val respository = SuperHeroRespository()

    suspend operator fun invoke(chunkNumber: Int): ArrayList<SuperHeroModel?> {
        val superHeroModels = ArrayList<SuperHeroModel?>()
        var start = chunkNumber*10-9
        val end = chunkNumber*10
        val obsList = ArrayList<Observable<SuperHeroModel>>()

        while (start <= end) {
            obsList.add(Observable.just(respository.getSuperHero(start.toString())))
            start++
        }

        Observable.concat(obsList).subscribe{
            superHeroModels.add(it)
        }

        return superHeroModels
    }

}