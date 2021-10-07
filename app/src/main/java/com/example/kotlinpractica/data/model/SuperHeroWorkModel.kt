package com.example.kotlinpractica.data.model

import com.google.gson.annotations.SerializedName

data class SuperHeroWorkModel(
    @SerializedName("occupation") val occupation: String,
    @SerializedName("base") val base: String,
)