package com.pasukanlangit.id.unittesttdd.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pasukanlangit.id.unittesttdd.data.remote.model.DogResponse
import com.pasukanlangit.id.unittesttdd.utils.Constant
import com.pasukanlangit.id.unittesttdd.utils.DbConverter

@Database(entities = [ShoppingItem::class, DogResponse::class], version = Constant.DATABASE_VERSION)
@TypeConverters(DbConverter::class)
abstract class MyDatabase : RoomDatabase(){
    abstract fun shoppingDao(): ShoppingDao
    abstract fun dogDao(): DogDao
}