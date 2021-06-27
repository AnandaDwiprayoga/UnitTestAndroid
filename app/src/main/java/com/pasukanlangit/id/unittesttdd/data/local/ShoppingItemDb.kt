package com.pasukanlangit.id.unittesttdd.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShoppingItem::class], version = 1)
abstract class ShoppingItemDb : RoomDatabase(){
    abstract fun shoppingDao(): ShoppingDao
}