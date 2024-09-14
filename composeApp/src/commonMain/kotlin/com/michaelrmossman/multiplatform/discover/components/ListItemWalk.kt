package com.michaelrmossman.multiplatform.discover.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.theme.green
import com.michaelrmossman.multiplatform.discover.theme.orange
import com.michaelrmossman.multiplatform.discover.utils.getDayNightIconColor
import com.michaelrmossman.multiplatform.discover.utils.Constants.DIMEN_HORIZONTAL_SPACING
import com.michaelrmossman.multiplatform.discover.utils.Constants.DIMEN_ITEM_BORDER_SHAPE
import com.michaelrmossman.multiplatform.discover.utils.Constants.DIMEN_ITEM_PADDING
import com.michaelrmossman.multiplatform.discover.utils.Constants.ROUTE_CITY_CUTOFF
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.route_no_desc
import org.jetbrains.compose.resources.stringResource

@Composable
fun ListItemWalk(
    item: Routes,
    onClick: () -> Unit
) {
    val borderShape = RoundedCornerShape(DIMEN_ITEM_BORDER_SHAPE.dp)

    Row(
        modifier = Modifier
            .border(1.dp, Color.Gray, borderShape)
            .clickable(onClick = onClick)
            .padding(DIMEN_ITEM_PADDING.dp),
        horizontalArrangement = Arrangement.spacedBy(
            DIMEN_HORIZONTAL_SPACING.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconListItem(
            imageVector = Icons.Outlined.Route,
            tint = when (item.city) {
                0.0 -> getDayNightIconColor()
                else -> when (item.city < ROUTE_CITY_CUTOFF) {
                    true -> green /* colours from app theme */
                    else -> orange
                }
            }
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            item.name?.let { name ->
                Text(
                    fontWeight = FontWeight.Bold,
                    text = name
                )
            }

            Text(
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                /* Note elvis op */
                text = item.desc ?: stringResource(
                    resource = Res.string.route_no_desc
                )
            )
        }
    }
}