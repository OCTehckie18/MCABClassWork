package com.example.mcabclasswork.mainframe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ClassActivities(navController: NavController){

    LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            listOf(

                Pair({ navController.navigate(Routes.LifecycleSplash) },"In-Lab: Activity Lifecycle Demo"),
                Pair({ navController.navigate(Routes.POPStack) },"In-Class: Dashboard Page"),
                Pair({ navController.navigate(Routes.ShoppingCart) },"In-Class: Shopping Cart")

            ).forEach{ (id, name)->
                item {
                    Button(onClick = id) {
                        Text(name)
                    }
                }

            }
        }
}