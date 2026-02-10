package com.example.mcabclasswork.Labs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mcabclasswork.R

@Composable
fun Lab1() {
    var userName by remember { mutableStateOf("") }
    var showGreeting by remember { mutableStateOf(false) }

    if (!showGreeting) {
        UsernamePassword(onLoginSuccess = {
            userName = it
            showGreeting = true
        })
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            GreetingImage(message = "Happy Birthday,", from = userName)
            Button(
                onClick = { showGreeting = false },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
            ) {
                Text("Back to Input")
            }
        }
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 60.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Color.White,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(4f, 4f),
                    blurRadius = 8f
                )
            )
        )
        Text(
            text = from,
            fontSize = 36.sp,
            color = Color.Blue,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End)
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.longbirthday)
    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            alpha = 0.3f,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Composable
fun UsernamePassword(onLoginSuccess: (String) -> Unit) {
    var inputUserName by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Birthday Greeting App",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputUserName,
            onValueChange = { inputUserName = it },
            label = { Text("Enter Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (inputUserName.isNotBlank()) {
                onLoginSuccess(inputUserName)
            }
        }) {
            Text(text = "Generate Greeting")
        }
    }
}
