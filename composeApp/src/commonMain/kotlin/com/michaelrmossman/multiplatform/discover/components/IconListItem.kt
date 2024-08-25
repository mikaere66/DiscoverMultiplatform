package com.michaelrmossman.multiplatform.discover.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.utils.getDayNightIconColor

@Composable
fun IconListItem(
    imageVector: ImageVector,
    tint: Color = getDayNightIconColor()
) {
    Icon(
        contentDescription = null,
        imageVector = imageVector,
        modifier = Modifier
            .padding(start = 16.dp)
            .size(40.dp),
        tint = tint
    )
}