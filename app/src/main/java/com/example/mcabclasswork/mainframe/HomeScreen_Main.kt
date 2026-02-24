package com.example.mcabclasswork.mainframe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.launch

enum class HomeScreentabs(val title: String) {
    LABS("Labs"),
    CLASS_ACTIVITIES("Class Activities")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = { HomeScreentabs.entries.size })
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text("Lab Works Portfolio - 2547237") }
                )

                TabRow(
                    selectedTabIndex = pagerState.currentPage
                ) {
                    HomeScreentabs.entries.forEachIndexed { index, tab ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = { Text(tab.title) },
                            icon = {
                                Icon(
                                    imageVector = if (index == 0) Icons.Default.Home else Icons.Default.Person,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) { page ->
            when (HomeScreentabs.entries[page]) {
                HomeScreentabs.LABS -> Labs(navController)
                HomeScreentabs.CLASS_ACTIVITIES -> ClassActivities(navController)
            }
        }
    }
}
