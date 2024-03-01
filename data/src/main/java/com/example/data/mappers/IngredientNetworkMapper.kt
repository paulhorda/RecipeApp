package com.example.data.mappers

import com.example.data.network.models.IngredientNetwork
import com.example.domain.models.Ingredient

fun  List<IngredientNetwork>.toDomain(): List<Ingredient>{
    return this.map {
        Ingredient(
            id = it.id,
            name = it.name,
            original = it.original,
            amount = it.amount,
            unit = it.unit
        )
    }
}