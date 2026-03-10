package com.example.mcabclasswork.Labs.Lab8.dataClasses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mcabclasswork.Labs.Lab8.utils.AttendeeDao

@Database(entities = [Attendee::class], version = 1, exportSchema = false)
abstract class ConferenceDatabase : RoomDatabase() {
    abstract fun attendeeDao(): AttendeeDao

    companion object {
        @Volatile
        private var INSTANCE: ConferenceDatabase? = null

        fun getDatabase(context: Context): ConferenceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ConferenceDatabase::class.java,
                    "conference_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}