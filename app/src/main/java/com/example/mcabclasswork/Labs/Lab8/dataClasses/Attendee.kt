package com.example.mcabclasswork.Labs.Lab8.dataClasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendees_table")
data class Attendee(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int,
    val phoneNumber: String
)