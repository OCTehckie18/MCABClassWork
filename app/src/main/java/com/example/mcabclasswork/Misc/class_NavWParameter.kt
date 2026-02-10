package com.example.mcabclasswork.Misc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mcabclasswork.mainframe.Routes

@Composable
fun NavWParameter(navController: NavController){
    var name by remember {mutableStateOf("")}

    // this is screenA
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "It's Screen A. Give your name!",
            color = Color.Blue
        )
        Spacer(
            Modifier.height(16.dp)
        )
        // username ui and logic
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Name") }
        )

        Button(
            onClick = {
                navController.navigate(Routes.screenB+"/$name")
            }
        ) {
            Text(text="Login")
        }
    }
    // to goto screenB
}