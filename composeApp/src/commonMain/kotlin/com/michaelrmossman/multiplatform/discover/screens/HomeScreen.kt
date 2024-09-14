package com.michaelrmossman.multiplatform.discover.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsWalk
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.PedalBike
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.composegears.tiamat.NavBackHandler
import com.composegears.tiamat.NavDestination
import com.composegears.tiamat.NavDestinationScope
import com.composegears.tiamat.Navigation
import com.composegears.tiamat.koin.koinSharedTiamatViewModel
import com.composegears.tiamat.navController
import com.composegears.tiamat.navDestination
import com.composegears.tiamat.rememberNavController
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import com.michaelrmossman.multiplatform.discover.tabs.CommTab
import com.michaelrmossman.multiplatform.discover.tabs.FaveTab
import com.michaelrmossman.multiplatform.discover.tabs.TranTab
import com.michaelrmossman.multiplatform.discover.tabs.WalkTab
import com.michaelrmossman.multiplatform.discover.utils.iSiOS
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.bar_community
import discovermultiplatform.composeapp.generated.resources.bar_faves
import discovermultiplatform.composeapp.generated.resources.bar_transit
import discovermultiplatform.composeapp.generated.resources.bar_walks
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.outlined.Bicycle
import io.github.alexzhirkevich.cupertino.icons.outlined.Building2
import io.github.alexzhirkevich.cupertino.icons.outlined.FigureWalk
import io.github.alexzhirkevich.cupertino.icons.outlined.Heart
import org.jetbrains.compose.resources.stringResource

val homeScreen by navDestination<Unit> {

    val rootNavController = navController()

    val commTab by navDestination<Unit> {
        CommTab(
            viewModel = koinSharedTiamatViewModel<MainViewModel>()
        )
    }
    val faveTab by navDestination<Unit> {
        FaveTab(
            viewModel = koinSharedTiamatViewModel<MainViewModel>()
        )
    }
    val tranTab by navDestination<Unit> {
        TranTab(
            viewModel = koinSharedTiamatViewModel<MainViewModel>()
        )
    }
    val walkTab by navDestination<Unit> {
        WalkTab(
            navController = rootNavController,
            viewModel = koinSharedTiamatViewModel<MainViewModel>()
        )
    }

    val tabsList: Array<NavDestination<*>> = arrayOf(
        commTab, tranTab, walkTab, faveTab // Note different order
    )
    val tabs = remember { tabsList }
    val tabsNavController = rememberNavController(
        key = "tabNavigation",
        startDestination = commTab,
        destinations = tabsList
    )
    val canGoBack by remember {
        derivedStateOf {
            tabsNavController.canGoBack || tabsNavController.current != commTab
        }
    }
    val backAction: () -> Unit = remember {
        {
            if (tabsNavController.current != tabs[0]) {
                tabsNavController.replace(tabs[0])
            }
        }
    }

    Column(Modifier.fillMaxSize()) {
        NavBackHandler(canGoBack, backAction)
        Navigation(
            handleSystemBackEvent = false,
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth(),
            navController = tabsNavController
        )
        NavigationBar { // TODO: NavRail/Drawer
            tabs.onEachIndexed { index, tab ->
                val label = getLabel(index)
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = getIcon(index),
                            contentDescription = label
                        )
                    },
                    label = { Text(label) },
                    onClick = { tabsNavController.replace(tab) },
                    selected = tabsNavController.current == tab
                )
            }
        }
    }
}

private fun getIcon(index: Int): ImageVector {
    return when (index) {
        3 -> when (iSiOS) {
            true -> CupertinoIcons.Outlined.Heart
            else -> Icons.Outlined.Favorite
        }
        2 -> when (iSiOS) {
            true -> CupertinoIcons.Outlined.FigureWalk
            else -> Icons.AutoMirrored.Outlined.DirectionsWalk
        }
        1 -> when (iSiOS) {
            true -> CupertinoIcons.Outlined.Bicycle
            else -> Icons.Outlined.PedalBike
        }
        else -> when (iSiOS) {
            true -> CupertinoIcons.Outlined.Building2
            else -> Icons.Outlined.LocationCity
        }
    }
}

@Composable
private fun getLabel(index: Int): String {
    return when (index) {
        3 -> stringResource(Res.string.bar_faves)
        2 -> stringResource(Res.string.bar_walks)
        1 -> stringResource(Res.string.bar_transit)
        else -> {
            stringResource(Res.string.bar_community)
        }
    }
}