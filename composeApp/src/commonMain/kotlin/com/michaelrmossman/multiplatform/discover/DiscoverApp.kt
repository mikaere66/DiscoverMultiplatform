package com.michaelrmossman.multiplatform.discover

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.composegears.tiamat.NavController
import com.composegears.tiamat.Navigation
import com.composegears.tiamat.StorageMode
import com.composegears.tiamat.rememberNavController
import com.michaelrmossman.multiplatform.discover.screens.homeScreen
import com.michaelrmossman.multiplatform.discover.screens.walkScreen
import com.michaelrmossman.multiplatform.discover.theme.AppTheme

@Composable
fun DiscoverApp(
    configure: @Composable (
        NavController,
        @Composable () -> Unit
    ) -> Unit = { _, content ->
        content()
    }
) {

    AppTheme {

        Surface(Modifier.fillMaxSize()) {

//            @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
//            val windowSizeClass: WindowSizeClass = calculateWindowSizeClass()

//            val walkScreen by navDestination<NavArgsData> {
//                WalkScreen(
//                // navController = navController(),
//                    viewModel = koinSharedTiamatViewModel<MainViewModel>()
//                )
//            }

            val navController = rememberNavController(
                key = "rootNavController",
                storageMode = StorageMode.SavedState,
                startDestination = homeScreen,
                destinations = arrayOf(
                    homeScreen,
                    walkScreen
                )
            )
            configure(navController) {
                Navigation(
                    navController = navController,
                    modifier = Modifier.fillMaxSize().systemBarsPadding()
                )
            }
        }
    }
}