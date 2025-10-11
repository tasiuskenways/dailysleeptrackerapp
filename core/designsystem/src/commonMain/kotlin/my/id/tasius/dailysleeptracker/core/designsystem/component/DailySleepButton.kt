package my.id.tasius.dailysleeptracker.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import my.id.tasius.dailysleeptracker.core.designsystem.theme.DailySleepTheme
import my.id.tasius.dailysleeptracker.core.designsystem.theme.DailySleepThemeTokens
import my.id.tasius.dailysleeptracker.core.designsystem.theme.DailySleepThemeValues
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DailySleepButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: DailySleepButtonStyle = DailySleepButtonStyle.Primary,
    onClick: () -> Unit,
) {
    val colors = when (style) {
        DailySleepButtonStyle.Primary -> ButtonDefaults.buttonColors(
            containerColor = DailySleepThemeTokens.primaryBlue,
            contentColor = DailySleepThemeValues.colorScheme.onPrimary,
            disabledContainerColor = DailySleepThemeTokens.primaryBlue.copy(alpha = 0.4f),
            disabledContentColor = DailySleepThemeValues.colorScheme.onPrimary.copy(alpha = 0.7f),
        )

        DailySleepButtonStyle.Secondary -> ButtonDefaults.buttonColors(
            containerColor = DailySleepThemeTokens.accentPurple,
            contentColor = DailySleepThemeValues.colorScheme.onSecondary,
            disabledContainerColor = DailySleepThemeTokens.accentPurple.copy(alpha = 0.4f),
            disabledContentColor = DailySleepThemeValues.colorScheme.onSecondary.copy(alpha = 0.7f),
        )
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        shape = DailySleepButtonDefaults.Shape,
        contentPadding = DailySleepButtonDefaults.ContentPadding,
    ) {
        Text(
            text = text,
            style = DailySleepThemeValues.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

sealed interface DailySleepButtonStyle {
    data object Primary : DailySleepButtonStyle
    data object Secondary : DailySleepButtonStyle
}

object DailySleepButtonDefaults {
    val ContentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    val Shape: Shape = RoundedCornerShape(24.dp)
}

@Preview
@Composable
private fun DailySleepButtonPreview() {
    DailySleepTheme {
        DailySleepButton(
            text = "Primary Button",
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun DailySleepButtonSecondaryPreview() {
    DailySleepTheme {
        DailySleepButton(
            text = "Secondary Button",
            modifier = Modifier.fillMaxWidth(),
            style = DailySleepButtonStyle.Secondary,
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun DailySleepButtonShowcasePreview() {
    DailySleepTheme {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DailySleepButton(
                text = "Primary Button",
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            )

            DailySleepButton(
                text = "Secondary Button",
                modifier = Modifier.fillMaxWidth(),
                style = DailySleepButtonStyle.Secondary,
                onClick = {},
            )
        }
    }
}
