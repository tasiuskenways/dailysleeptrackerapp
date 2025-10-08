package my.id.tasius.dailysleeptracker.core.designsystem

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import my.id.tasius.dailysleeptracker.core.designsystem.theme.DailySleepColors
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Represents the colors used by the shimmer placeholder.
 */
data class ShimmerColors(
    val container: Color,
    val highlight: Color,
)

object ShimmerDefaults {

    /**
     * Default shimmer colors that adapts automatically to the active theme.
     */
    @Composable
    fun colors(): ShimmerColors {
        return if (isSystemInDarkTheme()) {
            ShimmerColors(
                container = Color.White.copy(alpha = 0.08f),
                highlight = Color.White.copy(alpha = 0.24f),
            )
        } else {
            ShimmerColors(
                container = DailySleepColors.LightTextSecondary.copy(alpha = 0.12f),
                highlight = DailySleepColors.LightTextSecondary.copy(alpha = 0.35f),
            )
        }
    }

    /**
     * Default placeholder shape.
     */
    val Shape: Shape = RoundedCornerShape(16.dp)

    /**
     * Default duration of the shimmer animation.
     */
    const val AnimationDurationMillis: Int = 1200

    /**
     * Default width of the highlight relative to the placeholder width.
     */
    const val HighlightWidthFraction: Float = 0.25f
}

/**
 * Applies an animated shimmer background to the [Modifier].
 */
fun Modifier.shimmerPlaceholder(
    visible: Boolean = true,
    shape: Shape = ShimmerDefaults.Shape,
    durationMillis: Int = ShimmerDefaults.AnimationDurationMillis,
    shimmerColors: ShimmerColors = ShimmerDefaults.colors(),
    highlightWidthFraction: Float = ShimmerDefaults.HighlightWidthFraction,
): Modifier = if (!visible) {
    this
} else {
    composed {
        val transition = rememberInfiniteTransition(label = "shimmer")
        val animationProgress = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            label = "shimmer_progress",
        )

        val highlightColors = remember(shimmerColors) {
            listOf(
                shimmerColors.container,
                shimmerColors.highlight,
                shimmerColors.container,
            )
        }

        val highlightWidthFractionClamped = highlightWidthFraction.coerceIn(0.1f, 1f)

        this
            .clip(shape)
            .drawWithContent {
                val gradientWidth = size.width * highlightWidthFractionClamped
                val animatedX = (size.width + gradientWidth) * animationProgress.value - gradientWidth

                drawRect(color = shimmerColors.container)
                drawRect(
                    brush = Brush.linearGradient(
                        colors = highlightColors,
                        start = Offset(x = animatedX, y = 0f),
                        end = Offset(x = animatedX + gradientWidth, y = size.height),
                    ),
                    size = size,
                )
            }
    }
}

/**
 * Convenience composable that creates a shimmer placeholder box.
 */
@Composable
fun ShimmerPlaceholder(
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    shape: Shape = ShimmerDefaults.Shape,
    durationMillis: Int = ShimmerDefaults.AnimationDurationMillis,
    shimmerColors: ShimmerColors = ShimmerDefaults.colors(),
    highlightWidthFraction: Float = ShimmerDefaults.HighlightWidthFraction,
    minHeight: Dp = 80.dp,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier = modifier
            .defaultMinSize(minHeight = minHeight)
            .shimmerPlaceholder(
                visible = visible,
                shape = shape,
                durationMillis = durationMillis,
                shimmerColors = shimmerColors,
                highlightWidthFraction = highlightWidthFraction,
            ),
        content = content,
    )
}

@Preview
@Composable
private fun ShimmerPlaceholderPreview() {
    MaterialTheme {
        ShimmerPlaceholder()
    }
}
