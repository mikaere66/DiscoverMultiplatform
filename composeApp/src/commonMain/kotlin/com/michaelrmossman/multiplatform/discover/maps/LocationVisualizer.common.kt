package com.michaelrmossman.multiplatform.discover.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.michaelrmossman.multiplatform.discover.features.geometry.Coordinates

@Composable
expect fun LocationVisualizer(
    coords: Coordinates,
    modifier: Modifier,
    title: String?,
    // parentScrollEnableState: MutableState<Boolean>
)
