package com.sports.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.sports.designsystem.theme.AppTheme

@Composable
fun MainText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle =
        MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    textDecoration: TextDecoration? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        maxLines = maxLines,
        minLines = minLines,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        textDecoration = textDecoration,
    )
}

@Composable
@Preview(showBackground = true)
private fun MainTextPreview() {
    AppTheme {
        MainText(
            text = "Hello, World!",
            style =
                MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
        )
    }
}
