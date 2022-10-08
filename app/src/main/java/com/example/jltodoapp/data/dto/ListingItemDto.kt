package com.example.jltodoapp.data.dto

import com.example.jltodoapp.data.model.ListingItem


data class ListingItemDto(
    val id: Long,
    val name: String,
    val price: Double,
    val quantity: Long,
    val type: Int
)

fun ListingItemDto.toItem(): ListingItem {
    return ListingItem(
        id = id,
        name = name,
        price = price,
        quantity = quantity,
        type = type
    )
}