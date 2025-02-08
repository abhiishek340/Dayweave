package com.lifesyncpro.app.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

// Slide in/out animations
fun slideInAnimation(
    initialOffsetX: Int = 0,
    initialOffsetY: Int = 300
): EnterTransition = slideInVertically(
    initialOffsetY = { initialOffsetY },
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
) + fadeIn(animationSpec = tween(300))

fun slideOutAnimation(
    targetOffsetX: Int = 0,
    targetOffsetY: Int = 300
): ExitTransition = slideOutVertically(
    targetOffsetY = { targetOffsetY },
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
) + fadeOut(animationSpec = tween(300))

// Pulse animation
val pulseAnimation = infiniteRepeatable(
    animation = tween(1000, easing = FastOutSlowInEasing),
    repeatMode = RepeatMode.Reverse
)

// Scale animation
val scaleAnimation = keyframes {
    durationMillis = 400
    0.95f at 100
    1.05f at 200
    0.95f at 300
    1f at 400
}

// Custom spring animation spec
val springSpec = spring<Float>(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessLow
)

// Shimmer effect animation
val shimmerEffect = infiniteRepeatable(
    animation = tween(
        durationMillis = 1500,
        easing = LinearEasing,
        delayMillis = 200
    ),
    repeatMode = RepeatMode.Restart
) 