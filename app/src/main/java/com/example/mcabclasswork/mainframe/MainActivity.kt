package com.example.mcabclasswork.mainframe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mcabclasswork.Labs.Lab0
import com.example.mcabclasswork.Labs.Lab1
import com.example.mcabclasswork.Labs.Lab2
import com.example.mcabclasswork.Labs.Lab3_CampusConnectApp.Departments_CC
import com.example.mcabclasswork.Labs.Lab3_CampusConnectApp.EventDetails_CC
import com.example.mcabclasswork.Labs.Lab3_CampusConnectApp.HomeScreen_CC
import com.example.mcabclasswork.Labs.Lab3_CampusConnectApp.Notifications_CC
import com.example.mcabclasswork.Labs.Lab3_CampusConnectApp.ProfileScreen_CC
import com.example.mcabclasswork.Labs.Lab4_ViewModel.CounterViewModel
import com.example.mcabclasswork.Labs.Lab4_ViewModel.Lab4_Counter
import com.example.mcabclasswork.Labs.Lab5.Lab5_API
import com.example.mcabclasswork.Misc.LifecycleDemo
import com.example.mcabclasswork.Misc.LifecycleSplash
import com.example.mcabclasswork.Misc.NavWParameter
import com.example.mcabclasswork.Misc.ShoppingCart.ShoppingCartApp
import com.example.mcabclasswork.Misc.ViewModalDemo.MultiViewModelScreen
import com.example.mcabclasswork.Misc.inclass27012026.HomeScreen_Dashboard
import com.example.mcabclasswork.Misc.inclass27012026.Profile
import com.example.mcabclasswork.Misc.inclass27012026.Settings
import com.example.mcabclasswork.Misc.screenB
import com.example.mcabclasswork.ui.theme.MCABClassWorkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MCABClassWorkTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HomeScreen,
                        modifier = Modifier.Companion.padding(innerPadding)
                    ) {
                        composable(Routes.HomeScreen) {
                            HomeScreen(navController)
                        }
                        // homescreen composable
                        composable(Routes.Main_Labs){
                            Labs(navController)
                        }
                        composable(Routes.Main_ClassActivities){
                            ClassActivities(navController)
                        }


                        composable(Routes.Lab0) {
                            Lab0()
                        }
                        composable(Routes.Lab1) {
                            Lab1()
                        }
                        composable(Routes.Lab2) {
                            Lab2()
                        }
                        composable(Routes.LifecycleDemo) {
                            LifecycleDemo()
                        }
                        composable(Routes.LifecycleSplash) {
                            LifecycleSplash(navController)
                        }
                        composable(Routes.NavigationWithParameter) {
                            NavWParameter(navController)
                        }
                        composable(Routes.screenB + "/{name}") {
                            val name = it.arguments?.getString("name")
                            screenB(name ?: "No Arguments")
                        }
                        composable(Routes.POPStack) {
                            HomeScreen_Dashboard(navController)
                        }
                        composable(Routes.settings) {
                            Settings(navController)
                        }
                        composable(Routes.profile) {
                            Profile(navController)
                        }

                        // lab3 conposables
                        composable(Routes.Lab3) {
                            HomeScreen_CC(navController)
                        }
                        composable("home") { HomeScreen_CC(navController) }
                        composable("departments") { Departments_CC(navController) }
                        composable("notifications") { Notifications_CC() }
                        composable("profile") { ProfileScreen_CC(navController) }
                        composable("event_details/{dept}") {
                            EventDetails_CC(it.arguments?.getString("dept") ?: "")
                        }

                        composable(Routes.ShoppingCart) {
                            ShoppingCartApp()
                        }

                        //ViewModalDemo
                        composable(Routes.ViewModalDemo) {
                            MultiViewModelScreen()
                        }

                        //Lab4
                        composable(Routes.Lab4) {
                            val c: CounterViewModel = viewModel()
                            Lab4_Counter(c)
                        }

                        //Lab5 - API Calls
                        composable(Routes.Lab5) {
                            Lab5_API(navController)
                        }
                    }
                }
            }
        }
    }
}
