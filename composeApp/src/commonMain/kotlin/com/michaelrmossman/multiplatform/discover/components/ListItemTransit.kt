package com.michaelrmossman.multiplatform.discover.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.database.TransitItems

@Composable
fun ListItemTransit(
    horizontalPadding: Int,
    imageVector: ImageVector,
    item: TransitItems,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    val borderShape = RoundedCornerShape(10.dp)

    Row(
        modifier = modifier
            .border(1.dp, Color.Gray, borderShape)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(
            horizontalPadding.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconListItem(imageVector = imageVector)

        Column (
            modifier = Modifier
                .weight(1F)
                .padding(8.dp),
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = item.name
            )

            Text(text = item.text)
        }

        if (item.extn) {
            IconListItem(
                imageVector = Icons.AutoMirrored.Outlined.OpenInNew
            )
            /* Docs : division between integer numbers always returns
               an integer number. Any fractional part is discarded */
            Spacer(Modifier.width(horizontalPadding.div(2).dp))
        }
    }
}