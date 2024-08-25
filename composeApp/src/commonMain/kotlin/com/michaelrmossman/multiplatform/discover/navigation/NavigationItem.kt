package com.michaelrmossman.multiplatform.discover.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(

//    var badgeCount: Int? = null,
    val label: String,
    val navType: NavigationType,
    val route: String,
    var selected: Boolean = false,
    val selectedIcon  : ImageVector,
    val unselectedIcon: ImageVector
//    val icon: ImageVector
)
