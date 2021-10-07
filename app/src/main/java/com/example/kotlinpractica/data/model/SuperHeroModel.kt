package com.example.kotlinpractica.data.model

import com.google.gson.annotations.SerializedName

data class SuperHeroModel(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperHeroImageModel,
    @SerializedName("powerstats") val powerStats: SuperHeroPowerStatsModel,
    @SerializedName("biography") val biography: SuperHeroBiographyModel,
    @SerializedName("appearance") val appearance: SuperHeroAppearanceModel,
    @SerializedName("work") val work: SuperHeroWorkModel,
    @SerializedName("connections") val connections: SuperHeroConnectionsModel,
)