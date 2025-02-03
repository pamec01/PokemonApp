package com.kotlin.pokemonapp.feature.pokemon.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonModel
import com.kotlin.pokemonapp.ui.theme.PokemonAppTheme

@Composable
fun PokemonListCard(
    pokemonModel: PokemonModel,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .background(
                color = Color(0xFF48D0B0L),
                shape = RoundedCornerShape(15.dp)
            )
            .fillMaxWidth()
    ) {

        AsyncImage(
            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/${pokemonModel.id}.png",
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .width(130.dp)
                .padding(8.dp)
        )

        Text(
            text = "#${pokemonModel.id}",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp),
            color = Color(0xFF3E8570)
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                pokemonModel.name.capitalize(),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 14.sp
                ),
                color = Color.White
            )
            TypeText(type = "Grass")
            TypeText(type = "Potion")
        }

    }
}

@Composable
private fun TypeText(
    modifier: Modifier = Modifier,
    type: String,
) {
    Text(
        type, color = Color.White,
        style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight(700),
            fontSize = 10.sp
        ),
        modifier = modifier
            .background(Color(0xFF5cdfc6), shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 6.dp, vertical = 4.dp)
    )
}

@Preview
@Composable
private fun PokemonListPreview() {
    PokemonAppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(10) {
                PokemonListCard(
                    pokemonModel = PokemonModel(
                        id = "0",
                        name = "Bulbasaur"
                    )
                )
            }
        }
    }
}