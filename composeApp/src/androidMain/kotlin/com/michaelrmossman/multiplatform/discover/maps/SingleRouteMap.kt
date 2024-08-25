package com.michaelrmossman.multiplatform.discover.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.theme.cccBarneyPurple
import com.michaelrmossman.multiplatform.discover.theme.cccBlack
import com.michaelrmossman.multiplatform.discover.theme.cccNiceBlue
import com.michaelrmossman.multiplatform.discover.theme.cccPumpkin
import com.michaelrmossman.multiplatform.discover.utils.Constants.CLOSED_STRING
import com.michaelrmossman.multiplatform.discover.utils.Constants.CONNECTOR_STRING
import com.michaelrmossman.multiplatform.discover.utils.Constants.LAT_LNG_BOUNDS_PADDING
import com.michaelrmossman.multiplatform.discover.utils.Constants.SHARED_NO_STRING
import com.michaelrmossman.multiplatform.discover.utils.Constants.SHARED_YES_STRING
import com.michaelrmossman.multiplatform.discover.utils.MapUtils.getRouteCenterPoint
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.route_end_point
import org.jetbrains.compose.resources.stringResource

// https://medium.com/@ridvanozcan48/how-to-use-google-maps-in-jetpack-compose-step-by-step-android-guide-55aedac89e43
// https://www.darrylbayliss.net/jetpack-compose-for-maps/
@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun SingleRouteMap(
    fullScreen: Boolean,
    route: RouteKt
) {
    val latLngList = remember {
        route.coords.map { coords ->
            LatLng(coords.lati, coords.long)
        }
    }
    val routeFirst = remember {
        mutableStateOf(LatLng(route.lati, route.long))
    }
    // https://stackoverflow.com/questions/75425433/how-to-use-null-as-default-value-compose-mutablestate
    val routeLast = remember {
        mutableStateOf<LatLng?>(null)
    }
    val routeCenter = remember {
        getRouteCenterPoint(latLngList)
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(routeCenter,14F)
    }
    val color = if (route.shar == SHARED_NO_STRING) {
        cccBarneyPurple // 79
    } else if (route.shar == SHARED_YES_STRING) {
        cccPumpkin // 30
    } else if (route.name?.contains(CONNECTOR_STRING, ignoreCase = true) == true) {
        cccNiceBlue // 12
    } else if (route.stat == CLOSED_STRING) {
        cccBlack
    } else {
        Color.Red
    }

    // https://stackoverflow.com/questions/77622240/how-to-automatically-adjust-zoom-to-accommodate-all-markers-in-google-maps-compo
    LaunchedEffect(key1 = Unit) {
        if (latLngList.size > 1) {
            routeLast.value = latLngList[
                latLngList.size.minus(1)
            ]
        }
        val boundsBuilder = LatLngBounds.builder()
        latLngList.forEach { latLng ->
            boundsBuilder.include(latLng)
        }
        cameraPositionState.move(
            update = CameraUpdateFactory.newLatLngBounds(
                boundsBuilder.build(),
                LAT_LNG_BOUNDS_PADDING // 120
            )
        )
    }

    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = fullScreen
            )
        )
    }

    GoogleMap(
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings
    ) {
        Marker(
            state = MarkerState(position = routeFirst.value),
            title = route.name
        )
        Polyline(
            points = latLngList,
            clickable = false,
            color = color,
            width = 12.5F
        )
        routeLast.value?.let { latLng ->
            Marker(
                state = MarkerState(position = latLng),
                title = stringResource(
                    resource = Res.string.route_end_point
                )
            )
        }
    }
}