package com.michaelrmossman.multiplatform.discover.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.michaelrmossman.multiplatform.discover.actions.ActionsWalks
import com.michaelrmossman.multiplatform.discover.navigation.NavigationType
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.app_name
import discovermultiplatform.composeapp.generated.resources.desc_navigate_back
import io.github.alexzhirkevich.cupertino.CupertinoNavigateBackButton
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTopAppBar
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveWidget
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAdaptiveApi::class, ExperimentalCupertinoApi::class)
@Composable
fun AppTopBar(
    onEvent: (MainListEvent) -> Unit,
    listState: MainListState,
    onNavigateUp: () -> Unit
) {
    val backTextOrDesc = stringResource(
        resource = Res.string.desc_navigate_back
    )

    AdaptiveTopAppBar(
        actions = {
            when (listState.currentNavType) {
                NavigationType.CommunityScreen -> { /* Not yet implemented */ }
                NavigationType.FavesScreen     -> { /* Not yet implemented */ }
                NavigationType.TransitScreen   -> { /* Not yet implemented */ }
                NavigationType.WalksScreen -> ActionsWalks(
                    onEvent = onEvent,
                    listState = listState
                )
            }
        },
        // https://stackoverflow.com/questions/69192042/how-to-use-jetpack-compose-app-bar-backbutton/70409412#70409412
        navigationIcon = {
            if (
                listState.currentNavType.name
                !=
                listState.startDestination.name
            ) {
                AdaptiveWidget(
                    cupertino = {
                        CupertinoNavigateBackButton(
                            onClick = { onNavigateUp() },
                        ) {
                            CupertinoText(backTextOrDesc)
                        }
                    },
                    material = {
                        IconButton(
                            onClick = { onNavigateUp() }
                        ) {
                            Icon(
                                contentDescription = backTextOrDesc,
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack
                            )
                        }
                    }
                )
            }
        },
        title = { Text(text = stringResource(resource = Res.string.app_name)) }
    )
}