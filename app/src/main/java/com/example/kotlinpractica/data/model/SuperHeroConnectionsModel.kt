package com.example.kotlinpractica.data.model

import com.google.gson.annotations.SerializedName

data class SuperHeroConnectionsModel(
    @SerializedName("group-affiliation") val groupAffiliation: String,
    @SerializedName("relatives") val relatives: String,
)