package com.michaelrmossman.multiplatform.discover.components

import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.michaelrmossman.multiplatform.discover.navigation.NavigationItem

@Composable
fun NavBarIcon(navItem: NavigationItem) {
    Icon(
        contentDescription = navItem.label,
        imageVector = when (navItem.selected) {
            true -> navItem.selectedIcon
            else -> navItem.unselectedIcon
        }
    )
}
