package com.example.pants.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PantsAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}