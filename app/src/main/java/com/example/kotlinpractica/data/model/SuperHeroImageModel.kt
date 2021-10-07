package com.example.kotlinpractica.data.model

import com.google.gson.annotations.SerializedName

data class SuperHeroImageModel(
    @SerializedName("url") val url: String,
)