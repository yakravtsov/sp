package com.example.yicameraprototype.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.yicameraprototype.R

@Composable
fun YiCameraPrototypeTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme(
        primary = colorResource(R.color.brand_primary),
        onPrimary = colorResource(R.color.brand_on_primary),
        secondary = colorResource(R.color.brand_primary),
        onSecondary = colorResource(R.color.brand_on_primary)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
