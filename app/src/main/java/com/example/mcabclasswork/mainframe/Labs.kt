package com.example.mcabclasswork.mainframe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Labs(navController: NavController){
    LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {


            listOf(
                Pair({ navController.navigate(Routes.Lab0) }, "Lab 0: Map game"),
                Pair({ navController.navigate(Routes.Lab1) }, "Lab 1: Birthday Greeting"),
                Pair({ navController.navigate(Routes.Lab2) }, "Lab 2: Calculator"),
                Pair({ navController.navigate(Routes.Lab3) }, "Lab 3: Campus Connect"),
            ).forEach { (i, name) ->
                item {
                    Button(
                        onClick = i,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Green, // background
                            contentColor = Color.Black // text
                        )
                    ) {
                        Text(name)
                    }
                }
            }
        }
}