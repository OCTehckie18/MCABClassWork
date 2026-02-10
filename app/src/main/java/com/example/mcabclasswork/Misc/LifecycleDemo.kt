package com.example.mcabclasswork.Misc

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.mcabclasswork.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun LifecycleDemo() {

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val logs = remember { mutableStateListOf<String>() }

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->

            val timestamp = SimpleDateFormat(
                "HH:mm:ss dd/MM/yyyy",
                Locale.getDefault()
            ).format(Date())

            val logEntry = "$timestamp  |  ${event.name}"

            logs.add(logEntry)
            Log.d("LifecycleDemo", logEntry)
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    Box(
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(R.drawable.seastorm),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .background(
                    color = Color.White.copy(alpha = 0.75f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(24.dp),   // space inside the box
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Log", color = Color.Blue)
            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(logs) {
                    Text(text = it, color = Color.Blue)
                }
            }
        }
    }



}
