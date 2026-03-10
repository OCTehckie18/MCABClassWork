package com.example.mcabclasswork.Labs.Lab8.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mcabclasswork.Labs.Lab8.dataClasses.Attendee
import com.example.mcabclasswork.Labs.Lab8.dataClasses.AttendeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConferenceApp(
    viewModel: AttendeeViewModel = viewModel(),
) {
    val context = LocalContext.current
    val attendees by viewModel.allAttendees.collectAsState()

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var editingAttendee by remember { mutableStateOf<Attendee?>(null) }
    var hasSmsPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted -> hasSmsPermission = isGranted }
    )

    // Request permission on launch if not granted
    LaunchedEffect(Unit) {
        if (!hasSmsPermission) permissionLauncher.launch(Manifest.permission.SEND_SMS)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CHRIST Univ Admin") },
                actions = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(if (editingAttendee == null) "Register New Attendee" else "Edit Attendee", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name, onValueChange = { name = it },
                label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = age, onValueChange = { age = it },
                label = { Text("Age") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = phone, onValueChange = { phone = it },
                label = { Text("Phone Number") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (name.isNotBlank() && age.isNotBlank() && phone.isNotBlank()) {
                        if (editingAttendee == null) {
                            viewModel.registerAttendee(name, age.toIntOrNull() ?: 0, phone, hasSmsPermission)
                        } else {
                            viewModel.updateAttendee(editingAttendee!!.copy(name = name, age = age.toIntOrNull() ?: 0, phoneNumber = phone))
                            editingAttendee = null
                        }
                        name = ""; age = ""; phone = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (editingAttendee == null) "Register & Send SMS" else "Update Attendee")
            }

            if (!hasSmsPermission) {
                Text("SMS permission missing. Confirmations will not be sent.", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            Text("Registered Attendees", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(attendees) { attendee ->
                    AttendeeItem(
                        attendee = attendee,
                        onEdit = {
                            editingAttendee = attendee
                            name = attendee.name
                            age = attendee.age.toString()
                            phone = attendee.phoneNumber
                        },
                        onDelete = { viewModel.deleteAttendee(attendee) }
                    )
                }
            }
        }
    }
}

@Composable
fun AttendeeItem(attendee: Attendee, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(attendee.name, style = MaterialTheme.typography.titleMedium)
                Text("Age: ${attendee.age} | Ph: ${attendee.phoneNumber}", style = MaterialTheme.typography.bodyMedium)
            }
            Row {
                IconButton(onClick = onEdit) { Icon(Icons.Default.Edit, contentDescription = "Edit", tint = MaterialTheme.colorScheme.primary) }
                IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error) }
            }
        }
    }
}