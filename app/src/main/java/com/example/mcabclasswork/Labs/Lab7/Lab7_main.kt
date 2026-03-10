package com.example.mcabclasswork.Labs.Lab7

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.edit

@Composable
fun Lab7_main(){
    val context = LocalContext.current

    // initialized sharedPreferences
    val sharedPreferences = remember{
        context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)
    }

    // State variables
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // loading of stored value
    LaunchedEffect(Unit) {
        name = sharedPreferences.getString("user_name", "") ?: ""
        email = sharedPreferences.getString("user_email", "") ?: ""
    }


    // UI Buildup
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "User Settings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label  = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = {email = it },
            label  = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                sharedPreferences.edit {
                    putString("user_name", name)
                    putString("user_email", email)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save Information")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display UI
        Text(
            text = "Stored Data: $name | $email",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}