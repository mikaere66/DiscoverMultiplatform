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
import androidx.compose.material.icons.outlined.Transform
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.desc_random_route
import androidx.compose.material.icons.Icons
import org.jetbrains.compose.resources.stringResource

@Composable
fun SharedStatText(
    iconSize: Int,
    onRandomClick: () -> Unit,
    routeKt: RouteKt,
    verticalArrangement: Arrangement.HorizontalOrVertical
) {
    val contentDescription = stringResource(
        resource = Res.string.desc_random_route
    )
    val imageVector = Icons.Outlined.Transform

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1F),
            verticalArrangement = verticalArrangement
        ) {
            Text(text = routeKt.shar)
            Text(text = routeKt.stat)
        }
        IconButton(
            onClick = {
                onRandomClick()
            }
        ) {
            Icon(
                contentDescription = contentDescription,
                imageVector = imageVector,
                modifier = Modifier.size(iconSize.dp),
                tint = Color.Green
            )
        }
    }
}