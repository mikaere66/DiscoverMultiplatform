@file:Suppress("UnusedReceiverParameter")

package com.michaelrmossman.multiplatform.discover.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.Composable
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.filled.Map
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons

val AdaptiveIcons.Filled.Map
    @Composable
    get() = AdaptiveIcons.vector(
        material = { Icons.Filled.Map },
        cupertino = { CupertinoIcons.Filled.Map }
    )
