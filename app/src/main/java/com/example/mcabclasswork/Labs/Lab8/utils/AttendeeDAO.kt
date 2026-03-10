package com.example.mcabclasswork.Labs.Lab8.utils

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mcabclasswork.Labs.Lab8.dataClasses.Attendee
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendeeDao {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendee(attendee: Attendee)

    // READ
    @Query("SELECT * FROM attendees_table ORDER BY name ASC")
    fun getAllAttendees(): Flow<List<Attendee>>

    // UPDATE
    @Update
    suspend fun updateAttendee(attendee: Attendee)

    // DELETE
    @Delete
    suspend fun deleteAttendee(attendee: Attendee)
}