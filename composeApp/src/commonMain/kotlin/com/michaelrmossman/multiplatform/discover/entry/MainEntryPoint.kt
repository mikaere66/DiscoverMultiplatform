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

            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> MainNavBar(
                    listState = listState,
                    navController = navController,
                    onClick = ::onClickHandler,
                    onEvent = viewModel::onEvent
                )
                WindowWidthSizeClass.Medium -> MainNavRail(
                    listState = listState,
                    navController = navController,
                    onClick = ::onClickHandler,
                    onEvent = viewModel::onEvent
                )
                WindowWidthSizeClass.Expanded -> MainNavDrawer(
                    listState = listState,
                    navController = navController,
                    onClick = ::onClickHandler,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}

fun onClickHandler(
    navController: NavHostController,
    navItem: NavigationItem,
    startDestination: NavigationType
) {
    navController.navigate(navItem.route) {
        popUpTo(
            startDestination.name
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}