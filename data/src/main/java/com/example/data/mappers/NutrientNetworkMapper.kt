package com.example.data.mappers

import com.example.data.network.models.NutrientNetwork
import com.example.domain.models.Nutrient

fun List<NutrientNetwork>.toDomain(): List<Nutrient>{
    return this.map{nutrient ->
        nutrient.toDomain()
    }
}

fun NutrientNetwork.toDomain(): Nutrient {
    return Nutrient(
        name, amount, unit
    )
}