package com.pedrobruno.appgastos.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pedrobruno.appgastos.ui.screens.SplashScreen

const val splashRoute = "splash_route"

fun NavGraphBuilder.splashScreen() {
    composable(splashRoute) {
        SplashScreen()
    }
}