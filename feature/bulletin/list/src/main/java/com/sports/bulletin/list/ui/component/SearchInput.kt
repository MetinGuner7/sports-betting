package com.sports.bulletin.list.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.component.domain.model.SportDomainModel
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.Cancel
import com.sports.designsystem.icons.Search
import com.sports.designsystem.theme.AppTheme
import com.sports.ui.component.AppTextField
import com.sports.ui.extentions.clearFocusOnKeyboardDismiss
import com.sports.ui.extentions.clickableWithoutRipple
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun SearchInput(
    value: String,
    onValueChange: (String) -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    AppTextField(
        modifier = Modifier
            .clearFocusOnKeyboardDismiss()
            .focusRequester(focusRequester)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape  = MaterialTheme.shapes.extraSmall,
            ),
        value = value,
        shape = MaterialTheme.shapes.medium,
        onValueChange = onValueChange,
        keyboardOptions =
            KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
        placeholder = "Ara",
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        trailingIcon = {
            if (value.isNotEmpty()) {
                Image(
                    modifier =
                        Modifier.clickableWithoutRipple (
                            onClick = {
                                onClearClick()
                                scope.launch {
                                    delay(1000L)
                                    try {
                                        focusRequester.requestFocus()
                                    } catch (e: IllegalStateException) {
                                        Timber.e("SearchInput", "Failed to request focus", e)
                                    }
                                }
                            }
                        ),
                    imageVector = AppIcons.Cancel,
                    contentDescription = null,
                )
            }
        },
        leadingIcon = {
            Image(
                modifier = Modifier.size(20.dp),
                imageVector = AppIcons.Search,
                contentDescription = null,
            )
        },
    )
}

@Preview(showBackground = true, name = "BulletinList - Error")
@Composable
private fun SportListItemErrorPreview() {
    AppTheme {

        Box (modifier = Modifier.padding(16.dp)){
            SearchInput(
                value = "",
                onValueChange = {},
                onClearClick = {}
            )
        }
    }
}
