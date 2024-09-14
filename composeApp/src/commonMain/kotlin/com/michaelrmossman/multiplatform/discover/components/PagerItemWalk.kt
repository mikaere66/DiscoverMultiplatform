package com.michaelrmossman.multiplatform.discover.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.entities.toRouteKt
import com.michaelrmossman.multiplatform.discover.utils.Constants.DIMEN_FONT_SIZE_ITEM_TITLE
import com.michaelrmossman.multiplatform.discover.utils.Constants.DIMEN_HORIZONTAL_PADDING
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PagerItemWalk(
//    horizontalPadding: Int,
    itemIndex: Int,
    modifier: Modifier = Modifier, // TODO
    onRandomClick: () -> Unit,
//    onWalkEvent: (WalkListEvent) -> Unit,
    route: Routes
) {
    val routeKt = route.toRouteKt()
    // Logger.d("HEY") { route.roId.toString() }

    val iconSize = 30
    val scrollState = rememberScrollState()
    val verticalArrangement = Arrangement.spacedBy(8.dp)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = DIMEN_HORIZONTAL_PADDING.dp,
                vertical = 8.dp
            )
            .verticalScroll(state = scrollState),
        verticalArrangement = verticalArrangement
    ) {
        Text(
            modifier = Modifier.padding(
                bottom = 4.dp
            ),
            fontSize = DIMEN_FONT_SIZE_ITEM_TITLE.sp,
            fontWeight = FontWeight.Bold,
            text = routeKt.name
        )
        AreaPlaceText(
            iconSize = iconSize,
            // onWalkEvent = onWalkEvent,
            routeKt = routeKt,
            verticalArrangement = verticalArrangement
        )
        DistDuraText(
            iconSize = iconSize,
            itemIndex = itemIndex,
            // onWalkEvent = onWalkEvent,
            routeKt = routeKt,
            verticalArrangement = verticalArrangement
        )
        Text(text = routeKt.dogs)
        SharedStatText(
            iconSize = iconSize,
            onRandomClick = onRandomClick,
            routeKt = routeKt,
            verticalArrangement = verticalArrangement
        )
        Text(text = routeKt.desc)
    }
}