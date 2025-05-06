package com.sports.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sports.designsystem.component.AppText
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.Check
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.LocalAppColors
import com.sports.ui.extentions.noRippleToggleable

private const val CHECKBOX_ANIMATION_MS = 200
private const val CHECKBOX_ANIMATION_MULTIPLIER = -0.5

@Composable
fun AppCheckbox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    size: Float = 20f,
    minHeight: Dp = 40.dp,
    checkedColor: Color = LocalAppColors.current.green300,
    uncheckedColor: Color = MaterialTheme.colorScheme.surface,
    label: String? = null,
    onValueChange: (Boolean) -> Unit,
) {
    val checkboxColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedColor)
    val density = LocalDensity.current
    val duration = CHECKBOX_ANIMATION_MS

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            modifier
                .heightIn(minHeight)
                .noRippleToggleable(
                    value = isChecked,
                    role = Role.Checkbox,
                    onValueChange = onValueChange,
                ),
    ) {
        Box(
            modifier =
                Modifier.size(size.dp)
                    .background(color = checkboxColor, shape = RoundedCornerShape(6.dp))
                    .border(
                        width = 1.dp,
                        color =
                            if (isChecked) Color.Transparent else MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(6.dp),
                    ),
            contentAlignment = Alignment.Center,
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter =
                    slideInHorizontally(animationSpec = tween(duration)) {
                        with(density) { (size * CHECKBOX_ANIMATION_MULTIPLIER).dp.roundToPx() }
                    } +
                            expandHorizontally(
                                expandFrom = Alignment.Start,
                                animationSpec = tween(duration),
                            ),
                exit = fadeOut(),
            ) {
                Icon(
                    modifier = Modifier.size(14.dp),
                    imageVector = AppIcons.Check,
                    contentDescription = null,
                    tint = uncheckedColor,
                )
            }
        }
        label?.let {
            AppText(
                modifier = Modifier.padding(start = 8.dp),
                text = label,
                style =
                    MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.secondary
                    ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RoundedCornerCheckboxPreview() {
    AppTheme {
        Column(Modifier.padding(16.dp)) {
            AppCheckbox(label = "Checkbox", isChecked = true, onValueChange = {})
            AppCheckbox(label = "Checkbox", isChecked = false, onValueChange = {})
        }
    }
}
