package com.example.mcabclasswork.Labs.Lab8.dataClasses


import android.app.Application
import android.os.Build
import android.telephony.SmsManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AttendeeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AttendeeRepository
    val allAttendees: StateFlow<List<Attendee>>

    init {
        val attendeeDao = ConferenceDatabase.getDatabase(application).attendeeDao()
        repository = AttendeeRepository(attendeeDao)
        allAttendees = repository.allAttendees.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    }

    fun registerAttendee(name: String, age: Int, phone: String, hasSmsPermission: Boolean) {
        val newAttendee = Attendee(name = name, age = age, phoneNumber = phone)
        viewModelScope.launch {
            repository.insert(newAttendee)
            if (hasSmsPermission) {
                sendSms(phone, name)
            }
        }
    }

    fun updateAttendee(attendee: Attendee) {
        viewModelScope.launch { repository.update(attendee) }
    }

    fun deleteAttendee(attendee: Attendee) {
        viewModelScope.launch { repository.delete(attendee) }
    }

    private fun sendSms(phoneNumber: String, name: String) {
        try {
            val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                getApplication<Application>().getSystemService(SmsManager::class.java)
            } else {
                @Suppress("DEPRECATION")
                SmsManager.getDefault()
            }
            
            val message = "Welcome to the AI Conference at CHRIST University, $name!"
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
