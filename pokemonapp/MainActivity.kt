package com.kotlin.pokemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.kotlin.pokemonapp.feature.pokemon.presentation.PokemonNavGraph
import com.kotlin.pokemonapp.ui.theme.PokemonAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonAppTheme {
                PokemonNavGraph(rememberNavController())
            }
        }
    }
}
