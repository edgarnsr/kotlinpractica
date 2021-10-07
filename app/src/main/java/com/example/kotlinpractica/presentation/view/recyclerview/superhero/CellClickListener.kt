package com.example.kotlinpractica.presentation.view.recyclerview.superhero

import com.example.kotlinpractica.data.model.SuperHeroModel

interface CellClickListener {
    fun onCellClickListener(data: SuperHeroModel)
}