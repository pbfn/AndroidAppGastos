package com.pedrobruno.appgastos.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation

const val authGraphRoute = "authGraph"

fun NavGraphBuilder.authGraph(
    onNavigateToSignUp: () -> Unit,
    onNavigateToSignIn: () -> Unit,
) {
    navigation(
        route = authGraphRoute,
        startDestination = signInRoute
    ) {
        signInScreen(onNavigateToSignUp = onNavigateToSignUp)
        signUpScreen(onNavigateToSignIn = onNavigateToSignIn)
    }
}

fun NavHostController.navigateToAuthGraph(
    navOptions: NavOptions? = androidx.navigation.navOptions {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
    }
) {
    navigate(authGraphRoute, navOptions)
}