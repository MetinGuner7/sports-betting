package com.sports.ui.extentions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role
import com.sports.designsystem.util.NoRippleInteractionSource

fun Modifier.clickableWithoutRipple(onClick: () -> Unit) =
    this.clickable(
        indication = null,
        interactionSource = NoRippleInteractionSource(),
        onClick = onClick,
    )

fun Modifier.clickableWithoutRipple(
    isEnabled: Boolean = true,
    role: Role? = null,
    onClick: () -> Unit,
) =
    this.clickable(
        indication = null,
        interactionSource = NoRippleInteractionSource(),
        onClick = onClick,
        role = role,
        enabled = isEnabled,
    )

fun Modifier.noRippleToggleable(
    value: Boolean,
    role: Role = Role.Checkbox,
    onValueChange: (Boolean) -> Unit,
): Modifier = composed {
    toggleable(
        value = value,
        role = role,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onValueChange = onValueChange,
    )
}
