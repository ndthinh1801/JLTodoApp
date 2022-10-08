package com.example.jltodoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jltodoapp.data.model.ListingItem

@Database(entities = [ListingItem::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun itemToSellDao(): ItemToSellDao
}