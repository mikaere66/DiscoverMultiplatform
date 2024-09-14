package com.michaelrmossman.multiplatform.discover.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.utils.Map
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.desc_show_map
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import org.jetbrains.compose.resources.stringResource

@Composable
fun AreaPlaceText(
    iconSize: Int,
//    onWalkEvent: (WalkListEvent) -> Unit,
    routeKt: RouteKt,
    verticalArrangement: Arrangement.HorizontalOrVertical
) {
    val contentDescription = stringResource(
        resource = Res.string.desc_show_map
    )
    val imageVector = AdaptiveIcons.Filled.Map

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1F),
            verticalArrangement = verticalArrangement
        ) {
            Text(text = routeKt.area)
            Text(text = routeKt.plac)
        }
        IconButton(
            onClick = {
//                onWalkEvent(
//                    WalkListEvent.SetCurrentScreen(
//                        screen = when (listState.currentScreen.value) {
//                            Screen.Route -> Screen.Map
//                            else         -> Screen.Route
//                        }
//                    )
//                )
            }
        ) {
            Icon(
                contentDescription = contentDescription,
                imageVector = imageVector,
                modifier = Modifier.size(iconSize.dp),
                tint = Color.Red
            )
        }
    }
}