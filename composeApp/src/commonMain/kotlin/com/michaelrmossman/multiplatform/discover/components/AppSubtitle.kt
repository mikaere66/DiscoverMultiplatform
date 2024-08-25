package com.michaelrmossman.multiplatform.discover.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.michaelrmossman.multiplatform.discover.navigation.NavigationType
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import org.jetbrains.compose.resources.stringResource
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.subtitle_base
import discovermultiplatform.composeapp.generated.resources.subtitle_community
import discovermultiplatform.composeapp.generated.resources.subtitle_faves
import discovermultiplatform.composeapp.generated.resources.subtitle_cycling
import discovermultiplatform.composeapp.generated.resources.subtitle_walks

@Composable
fun AppSubtitle(
    listState: MainListState
) {
    val fontSize = 18.sp
    val horizontalPadding = 16.dp
    val quantity = when (listState.currentNavType) {
        NavigationType.CommunityScreen -> listState.communityItems.size
        NavigationType.FavesScreen ->     listState.favourites.size
        NavigationType.TransitScreen ->   listState.transitItems.size
        NavigationType.WalksScreen ->     listState.routes.size
    }
    val screenSub = when (listState.currentNavType) {
        NavigationType.CommunityScreen -> stringResource(
            resource = Res.string.subtitle_community
        )
        NavigationType.FavesScreen -> stringResource(
            resource = Res.string.subtitle_faves
        )
        NavigationType.TransitScreen -> stringResource(
            resource = Res.string.subtitle_cycling
        )
        NavigationType.WalksScreen -> stringResource(
            resource = Res.string.subtitle_walks
        )
    }
    val subtitle = stringResource(
        resource = Res.string.subtitle_base,
        formatArgs = arrayOf(screenSub, quantity)
    )
    val verticalPadding = 8.dp

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            modifier = Modifier.padding(
                start = horizontalPadding,
                end = horizontalPadding,
                bottom = verticalPadding
            ),
            text = subtitle
        )
    }
}