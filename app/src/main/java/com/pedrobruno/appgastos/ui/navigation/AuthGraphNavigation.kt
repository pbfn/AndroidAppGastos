package com.pedrobruno.appgastos.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation

const val authGraphRoute = "authGraph"

fun NavGraphBuilder.authGraph(
    onNavigateToSignUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    navigation(
        route = authGraphRoute,
        startDestination = signInRoute
    ) {
        signInScreen(onNavigateToSignUp = onNavigateToSignUp, onNavigateToHome = onNavigateToHome)
        signUpScreen(onNavigateToSignIn = onNavigateToSignIn)
    }
}

fun NavHostController.navigateToAuthGraph() {
    navigate(authGraphRoute)
}