package com.example.jltodoapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemToSell")
data class ListingItem(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "quantity") val quantity: Long,
    @ColumnInfo(name = "type") val type: Int
) {
    override fun toString() = name
}
