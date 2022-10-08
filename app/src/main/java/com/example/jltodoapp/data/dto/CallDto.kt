package com.example.jltodoapp.data.dto

import com.example.jltodoapp.data.model.CallItem

data class CallDto(
    val id: Long,
    val name: String,
    val number: String
)

fun CallDto.toCallItem(): CallItem {
    return CallItem(
        id = id,
        name = name,
        number = number
    )
}