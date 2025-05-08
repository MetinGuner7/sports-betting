package com.sports.bulletin.detail.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sports.component.domain.model.Bookmaker
import com.sports.designsystem.component.AppText
import com.sports.designsystem.theme.MediumSpacer
import com.sports.designsystem.theme.semibold

@Composable
fun BookmakerHeader(bookmaker: Bookmaker) {
    Column(modifier = Modifier.fillMaxWidth()) {
        bookmaker.title?.let { title ->
            AppText(
                text = title,
                style = MaterialTheme.typography.titleLarge.semibold,
            )
        }
        MediumSpacer()
    }
}

