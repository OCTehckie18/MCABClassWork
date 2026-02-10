package com.example.mcabclasswork.mainframe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)

enum class HomeScreentabs{
    LABS, CLASS_ACTIVITIES
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(HomeScreentabs.LABS) }
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text("Lab Works Portfolio - 2547237") }
                )

                TabRow(
                    selectedTabIndex = selectedTab.ordinal
                ) {
                    Tab(
                        selected = selectedTab == HomeScreentabs.LABS,
                        onClick = { selectedTab = HomeScreentabs.LABS },
                        text = { Text("Labs") },
                        icon = { Icon(
                            Icons.Default.Home, contentDescription =
                            null) }
                    )
                    Tab(
                        selected = selectedTab == HomeScreentabs.CLASS_ACTIVITIES,
                        onClick = { selectedTab = HomeScreentabs.CLASS_ACTIVITIES },
                        text = { Text("Class Activities") },
                        icon = { Icon(Icons.Default.Person, contentDescription
                        = null) }
                    )

                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (selectedTab) {
                HomeScreentabs.LABS->Labs(navController)
                HomeScreentabs.CLASS_ACTIVITIES->ClassActivities(navController)
            }
        }
    }
}


