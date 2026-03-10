package com.example.mcabclasswork.Labs.Lab8.utils

import android.telephony.SmsManager

fun sendConfirmationSms(phoneNumber: String, attendeeName: String) {
    try {
        val smsManager: SmsManager = SmsManager.getDefault()
        val message = "Welcome to the AI Conference, $attendeeName!"

        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

