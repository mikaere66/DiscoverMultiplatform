package com.michaelrmossman.multiplatform.discover.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
internal expect fun systemImage(name : String) : Painter?