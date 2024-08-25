package com.michaelrmossman.multiplatform.discover.entry

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.michaelrmossman.multiplatform.discover.di.koinViewModel
import com.michaelrmossman.multiplatform.discover.navigation.NavigationItem
import com.michaelrmossman.multiplatform.discover.navigation.NavigationType
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import com.michaelrmossman.multiplatform.discover.theme.AppTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MainEntryPoint() {


    AppTheme {

        Surface(Modifier.fillMaxSize()) {

            val windowSizeClass: WindowSizeClass = calculateWindowSizeClass()
            val viewModel = koinViewModel<MainViewModel>()
            viewModel.onEvent(
                MainListEvent.SetWindowWidthSizeClass(
                    windowSizeClass.widthSizeClass
                )
            )
            val navController = rememberNavController()
            val listState by viewModel.state.collectAsState()
            val navItemStart = listState.navItems.find { navItem ->
                navItem.navType.name == listState.startDestination.name
            } /* Note : nullable ... refer to onClickHandler() below */
            val onNavigateUp = {
                onClickHandler(
                    navController,
                    navItemStart,
                    listState.startDestination
                )
                viewModel.onEvent(
                    MainListEvent.SetCurrentNavType(
                        navType = listState.startDestination
                    )
                )
            }

            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> MainNavBar(
                    listState = listState,
                    navController = navController,
                    onClick = ::onClickHandler,
                    onEvent = viewModel::onEvent,
                    onNavigateUp = { onNavigateUp() }
                )
                WindowWidthSizeClass.Medium -> MainNavRail(
                    listState = listState,
                    navController = navController,
                    onClick = ::onClickHandler,
                    onEvent = viewModel::onEvent,
                    onNavigateUp = { onNavigateUp() }
                )
                WindowWidthSizeClass.Expanded -> MainNavDrawer(
                    listState = listState,
                    navController = navController,
                    onClick = ::onClickHandler,
                    onEvent = viewModel::onEvent,
                    onNavigateUp = { onNavigateUp() }
                )
            }
        }
    }
}

/* navItem is nullable because on initialising onNavigateUp
   above, listState.navItems has not yet been populated */
fun onClickHandler(
    navController: NavHostController,
    navItem: NavigationItem?,
    startDestination: NavigationType
) {
    navItem?.route?.let { route ->
        navController.navigate(route) {
            popUpTo(
                startDestination.name
            ) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}