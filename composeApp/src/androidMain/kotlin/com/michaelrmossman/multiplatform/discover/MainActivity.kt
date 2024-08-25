package com.michaelrmossman.multiplatform.discover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.michaelrmossman.multiplatform.discover.entry.MainEntryPoint

class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        /* Handle the splash screen transition.
           Note how it comes BEFORE onCreate()
           On devices EARLIER than Android 12,
           all it does is prevent the blinding
           white that you see in Dark mode */
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MainEntryPoint()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    MainEntryPoint()
}