package com.example.mcabclasswork.Labs.Lab3_CampusConnectApp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

sealed class DrawerItem(val route: String, val title: String) {
    object Home : DrawerItem("home", "Home")
    object Departments : DrawerItem("departments", "Departments")
    object Profile : DrawerItem("profile", "Profile")
    object Logout : DrawerItem("logout", "Logout")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen_CC(navController: NavController) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                navController = navController,
                closeDrawer = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Campus Connect") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = { BottomBar(navController) }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Welcome to Campus Connect")
            }
        }
    }
}


@Composable
fun DrawerContent(
    navController: NavController,
    closeDrawer: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.surface)
    ) {

        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column {
                Text(
                    text = "Campus Connect",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Student Portal",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorScheme.onSurfaceVariant
                )
            }
        }

        HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

        Spacer(modifier = Modifier.height(8.dp))

        DrawerItemRow(
            title = "Home",
            icon = Icons.Default.Home
        ) {
            navigate(navController, closeDrawer, DrawerItem.Home.route)
        }

        DrawerItemRow(
            title = "Departments",
            icon = Icons.Default.Menu
        ) {
            navigate(navController, closeDrawer, DrawerItem.Departments.route)
        }

        DrawerItemRow(
            title = "Profile",
            icon = Icons.Default.Person
        ) {
            navigate(navController, closeDrawer, DrawerItem.Profile.route)
        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

        DrawerItemRow(
            title = "Logout",
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            isDestructive = true
        ) {
            navigate(navController, closeDrawer, DrawerItem.Logout.route)
        }
    }
}

@Composable
fun DrawerItemRow(
    title: String,
    icon: ImageVector,
    isDestructive: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = title,
            tint = if (isDestructive)
                colorScheme.error
            else
                colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isDestructive)
                colorScheme.error
            else
                colorScheme.onSurface
        )
    }
}

@Composable
fun BottomBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorScheme.surface)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomNavItem("Home", Icons.Default.Home) {
            navController.navigate("home") {
                popUpTo("home") { inclusive = false }
                launchSingleTop = true
            }
        }

        BottomNavItem("Notifications", Icons.Default.Notifications) {
            navController.navigate("notifications") {
                launchSingleTop = true
            }
        }

        BottomNavItem("Profile", Icons.Default.Person) {
            navController.navigate("profile") {
                launchSingleTop = true
            }
        }
    }
}

@Composable
fun BottomNavItem(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(icon, contentDescription = label)
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}


private fun navigate(
    navController: NavController,
    closeDrawer: () -> Unit,
    route: String
) {
    closeDrawer()
    navController.navigate(route) {
        launchSingleTop = true
    }
}


