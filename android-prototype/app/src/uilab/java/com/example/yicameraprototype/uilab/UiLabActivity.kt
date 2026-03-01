package com.example.yicameraprototype.uilab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.yicameraprototype.uilab.components.BottomNavBar
import com.example.yicameraprototype.uilab.components.TopInfoBar
import com.example.yicameraprototype.uilab.screens.DebugScreen
import com.example.yicameraprototype.uilab.screens.GalleryScreen
import com.example.yicameraprototype.uilab.screens.HomeScreen
import com.example.yicameraprototype.uilab.screens.SettingsScreen
import com.example.yicameraprototype.uilab.theme.UiLabTheme

class UiLabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiLabTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: "home"
    
    var wifiConnected by remember { mutableStateOf(true) }
    var cameraReady by remember { mutableStateOf(false) }
    var batteryLevel by remember { mutableIntStateOf(85) }

    Scaffold(

        topBar = {
            TopInfoBar(
                wifiConnected = wifiConnected,
                cameraReady = cameraReady,
                batteryLevel = batteryLevel
            )
        },
        bottomBar = {
            BottomNavBar(
                currentRoute = currentDestination,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo("home") { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            enterTransition = { fadeIn(animationSpec = tween(200)) },
            exitTransition = { fadeOut(animationSpec = tween(200)) },
            popEnterTransition = { fadeIn(animationSpec = tween(200)) },
            popExitTransition = { fadeOut(animationSpec = tween(200)) },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen()
            }
            composable("settings") {
                SettingsScreen()
            }
            composable("gallery") {
                GalleryScreen()
            }
            composable("debug") {
                DebugScreen()
            }
        }
    }
}
