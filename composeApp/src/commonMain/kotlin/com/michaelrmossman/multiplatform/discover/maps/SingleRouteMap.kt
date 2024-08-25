package com.michaelrmossman.multiplatform.discover.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.michaelrmossman.multiplatform.discover.entities.RouteKt

@Composable
expect fun SingleRouteMap(
    fullScreen: Boolean,
    route: RouteKt
)