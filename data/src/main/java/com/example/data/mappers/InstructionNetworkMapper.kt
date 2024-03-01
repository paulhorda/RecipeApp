package com.example.data.mappers

import com.example.data.network.models.InstructionStepNetwork
import com.example.domain.models.Instruction

fun List<InstructionStepNetwork>.toDomain(): List<Instruction> {
    return this.map { step ->
        Instruction(
            step.number, step.step
        )
    }
}

