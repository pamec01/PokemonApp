package com.kotlin.pokemonapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController, startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(onItemClicked = { itemId -> navController.navigate("details/$itemId") })
        }
        composable("details/{itemId}") { backStackEntry ->
            backStackEntry.arguments?.getString("itemId")?.toInt()?.let {
                DetailsScreen(it)
            }
        }
    }
}

@Composable
fun HomeScreen(
    onItemClicked: (id: Int) -> Unit,
) {
    val bunchOfItems = listOf(1, 2, 3, 4, 5, 6)

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.align(Alignment.Center),
            contentPadding = PaddingValues(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(bunchOfItems) { item ->
                Row {
                    Text("I'm an item with itemId=$item")
                    Button(onClick = { onItemClicked(item) }) {
                        Text("Go To Detail")
                    }
                }
            }
        }
    }
}

@Composable
fun DetailsScreen(
    itemId: Int,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("This is detail of the Item $itemId", modifier = Modifier.align(Alignment.Center))
    }
}
