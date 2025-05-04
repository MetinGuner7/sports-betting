package com.sports.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.designsystem.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    title: String? = null,
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier.padding(0.dp),
        containerColor = containerColor,
        contentWindowInsets = {
            BottomSheetDefaults.windowInsets.only(WindowInsetsSides.Bottom)
        },
        sheetState = sheetState,
        shape = shape,
        dragHandle = null,
        properties = properties,
        onDismissRequest = onDismissRequest,
        scrimColor = scrimColor,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            title?.let { AppCenterTopAppBar(title = it) }
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PreviewModalBottomSheet() {
    AppTheme { AppModalBottomSheet(title = "Deneme", onDismissRequest = {}) {} }
}
