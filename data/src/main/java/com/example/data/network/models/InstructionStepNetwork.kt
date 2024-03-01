package com.example.data.network.models

data class InstructionStepNetwork(
    val number: Int,
    val step: String,
    val ingredients: List<StepElementNetwork>,
    val equipment: List<StepElementNetwork>
)