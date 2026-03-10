package com.example.mcabclasswork.Misc.database_INCLASS.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wishlist")
data class WishlistItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String
)
