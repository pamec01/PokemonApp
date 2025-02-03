package com.kotlin.pokemonapp.feature.registration.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kotlin.pokemonapp.feature.registration.presentation.screen.allusers.AllUsersScreen
import com.kotlin.pokemonapp.feature.registration.presentation.screen.registartion.RegistrationScreen

@Composable
fun RegistrationNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController, startDestination = "register"
    ) {
        composable("register") {
            RegistrationScreen(onSuccess = { navController.navigate("allUsers") })
        }
        composable("allUsers") {
            AllUsersScreen()
        }
    }
}