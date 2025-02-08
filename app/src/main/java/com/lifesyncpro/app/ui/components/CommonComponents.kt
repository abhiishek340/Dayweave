package com.lifesyncpro.app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var scale by remember { mutableStateOf(1f) }
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = springSpec
    )

    Button(
        onClick = {
            scale = 0.95f
            onClick()
        },
        modifier = modifier
            .scale(animatedScale)
            .height(56.dp),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }

    LaunchedEffect(scale) {
        if (scale < 1f) {
            kotlinx.coroutines.delay(100)
            scale = 1f
        }
    }
}

@Composable
fun PulsingCard(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale by rememberInfiniteTransition().animateFloat(
        initialValue = 1f,
        targetValue = 1.02f,
        animationSpec = pulseAnimation
    )

    Card(
        modifier = modifier
            .scale(scale)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        content()
    }
}

@Composable
fun ShimmerLoadingCard(
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = shimmerEffect
    )

    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect(translateAnim)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect(translateAnim)
            )
        }
    }
}

@Composable
fun AnimatedProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = FastOutSlowInEasing)
    )

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${(animatedProgress * 100).toInt()}%",
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}

private fun Modifier.shimmerEffect(translateAnim: Float) = this
    .fillMaxWidth()
    .background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f)
            ),
            start = Offset(translateAnim - 1000f, 0f),
            end = Offset(translateAnim, 0f)
        )
    ) 