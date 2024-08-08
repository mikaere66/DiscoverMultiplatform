package com.michaelrmossman.multiplatform.discover.maps

import android.view.MotionEvent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.michaelrmossman.multiplatform.discover.features.geometry.Coordinates

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun LocationVisualizer(
    coords: Coordinates,
    modifier: Modifier,
    title: String?,
//    parentScrollEnableState: MutableState<Boolean>
) {
    val currentLocation = LatLng(coords.latitude, coords.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 10f)
    }
//    LaunchedEffect(cameraPositionState.isMoving) {
//        /* This code helps when using a Compose GoogleMap inside a scrollable container */
//        if (!cameraPositionState.isMoving) {
//            parentScrollEnableState.value = true
//        }
//    }
    GoogleMap(
//        modifier = modifier.pointerInteropFilter(
//            onTouchEvent = {
//                when (it.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                        parentScrollEnableState.value = false
//                        false
//                    }
//                    else -> true
//                }
//            }
//        ),
        cameraPositionState = cameraPositionState
    )
}
