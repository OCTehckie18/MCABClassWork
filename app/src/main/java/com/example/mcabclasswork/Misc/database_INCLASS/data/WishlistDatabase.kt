package com.example.mcabclasswork.Misc.database_INCLASS.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WishlistItem::class], version = 1)
abstract class WishlistDatabase : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao
}
