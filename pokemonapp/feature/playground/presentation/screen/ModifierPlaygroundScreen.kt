package com.kotlin.pokemonapp.feature.playground.presentation.screen

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.kotlin.pokemonapp.R
import com.kotlin.pokemonapp.ui.theme.PokemonAppTheme

@Composable
fun ModifierPlaygroundScreen() {
    val scrollState = rememberScrollState()
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Modifier playground", style = MaterialTheme.typography.displaySmall)
            Text("Order matters", style = MaterialTheme.typography.headlineSmall)

            Text(
                "Modifier.padding(16.dp).background(Color.Red)",
                style = MaterialTheme.typography.labelSmall
            )

            Box(
                Modifier
                    .padding(16.dp)
                    .background(Color.Red)
            ) {
                Text("Test1")
            }

            Text(
                "Modifier.background(Color.Red).padding(16.dp)",
                style = MaterialTheme.typography.labelSmall
            )

            Box(
                Modifier
                    .background(Color.Red)
                    .padding(16.dp)
            ) {
                Text("Test1")
            }

            Text("Other examples", style = MaterialTheme.typography.headlineSmall)

            Text(
                "Click Event and Rounded Corners", style = MaterialTheme.typography.labelSmall
            )

            var clicked by remember { mutableStateOf(false) }
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Magenta)
                .clickable { clicked = !clicked }
            ) {
                Text("clicked=$clicked", modifier = Modifier.align(Alignment.Center))
            }

            Text(
                "Alignment and Border", style = MaterialTheme.typography.labelSmall
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Red)
                    .padding(16.dp),
            ) {
                Text(text = "Centered Text", modifier = Modifier.align(Alignment.Center))
            }

            Text(
                "Custom Graphics with Modifier", style = MaterialTheme.typography.labelSmall
            )

            Box(modifier = Modifier
                .size(100.dp)
                .drawBehind {
                    drawCircle(Color.Blue, radius = size.minDimension / 2)
                })

            Text(
                "Gradient behind the text", style = MaterialTheme.typography.labelSmall
            )

            Text("Hello Gradient!", color = Color.White, modifier = Modifier
                .drawWithCache {
                    val brush = Brush.linearGradient(
                        listOf(
                            Color(0xFF9E82F0), Color(0xFF42A5F5)
                        )
                    )
                    onDrawBehind {
                        drawRoundRect(
                            brush, cornerRadius = CornerRadius(10.dp.toPx())
                        )
                    }
                }
                .padding(8.dp))

            Text(
                "Expand animation demo", style = MaterialTheme.typography.labelSmall
            )

            var isExpanded by remember { mutableStateOf(false) }
            val height by animateDpAsState(targetValue = if (isExpanded) 200.dp else 32.dp)
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(Color.Green, RoundedCornerShape(12.dp))
                .clickable { isExpanded = !isExpanded }) {
                Text("click me", modifier = Modifier.align(Alignment.Center))
            }


            Text(
                "Overlapping composables", style = MaterialTheme.typography.labelSmall
            )

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.TopStart)
                        .background(Color.Blue)
                )
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.TopStart)
                        .offset(50.dp, 0.dp)
                        .background(Color.Red, CircleShape)
                )
            }


            Text(
                "Marquee Scrolling Text", style = MaterialTheme.typography.labelSmall
            )

            val offset = remember { Animatable(-1000f) }
            LaunchedEffect(Unit) {
                offset.animateTo(
                    targetValue = 1000f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(2000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }

            Text(
                text = "Scrolling Text in Compose",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontFamily = FontFamily.Monospace,
                ),
                maxLines = 1,
                overflow = TextOverflow.Visible,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset { IntOffset(-offset.value.toInt(), 0) }
                    .background(Color.LightGray)
                    .padding(16.dp)
            )

            Text(
                "Rotation", style = MaterialTheme.typography.labelSmall
            )

            var x by remember { mutableFloatStateOf(0f) }
            var y by remember { mutableFloatStateOf(0f) }
            var z by remember { mutableFloatStateOf(0f) }

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(text = "Rotation x: $x")
                Slider(value = x, onValueChange = { x = it }, valueRange = 0f..360f)
                Text(text = "Rotation y: $y")
                Slider(value = y, onValueChange = { y = it }, valueRange = 0f..360f)
                Text(text = "Rotation z: $z")
                Slider(value = z, onValueChange = { z = it }, valueRange = 0f..360f)
            }

            Image(
                painter = painterResource(id = R.drawable.university),
                contentDescription = null,
                modifier = Modifier
                    .graphicsLayer {
                        this.rotationX = x
                        this.rotationY = y
                        this.rotationZ = z
                    }
            )

            Text(
                "Color animation", style = MaterialTheme.typography.labelSmall
            )

            val infiniteTransition = rememberInfiniteTransition(label = "infinite")
            val color by infiniteTransition.animateColor(
                initialValue = Color.Green,
                targetValue = Color.Blue,
                animationSpec = infiniteRepeatable(
                    animation = tween(2500, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "color"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .drawBehind {
                        drawRect(color)
                    }
            )
        }
    }
}

@Preview
@Composable
private fun ModifierPlaygroundPreview() {
    PokemonAppTheme {
        ModifierPlaygroundScreen()
    }
}