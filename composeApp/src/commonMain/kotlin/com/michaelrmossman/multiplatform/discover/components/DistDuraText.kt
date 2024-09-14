package com.michaelrmossman.multiplatform.discover.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.faves_add
import discovermultiplatform.composeapp.generated.resources.faves_rem
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.resources.stringResource

@Composable
fun DistDuraText(
    iconSize: Int,
    itemIndex: Int,
//    onWalkEvent: (WalkListEvent) -> Unit,
    routeKt: RouteKt,
    verticalArrangement: Arrangement.HorizontalOrVertical
) {
    val contentDescription = stringResource(
        resource = when (routeKt.time?.isNotBlank()) {
            true -> Res.string.faves_rem
            else -> Res.string.faves_add
        }
    )
    val imageVector = when (routeKt.time?.isNotBlank()) {
        true -> Icons.Filled.BookmarkRemove
        else -> Icons.Outlined.BookmarkAdd
    }
    // routeKt.time?.let { time -> Logger.d("HEY") { time } }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1F),
            verticalArrangement = verticalArrangement
        ) {
            Text(text = routeKt.dist)
            Text(text = routeKt.dura)
        }
        IconButton(
            onClick = {
//                onWalkEvent(
//                    WalkListEvent.ToggleFaveRoute(
//                        index = itemIndex,
//                        roId = routeKt.roId,
//                        time = routeKt.time
//                    )
//                )
            }
        ) {
            Icon(
                contentDescription = contentDescription,
                imageVector = imageVector,
                modifier = Modifier.size(iconSize.dp),
                tint = Color.Yellow
            )
        }
    }
}