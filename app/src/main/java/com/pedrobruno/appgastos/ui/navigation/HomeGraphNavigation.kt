package com.pedrobruno.appgastos.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

const val homeGraphRoute = "homeGraph"

fun NavGraphBuilder.homeGraph() {
    navigation(
        route = homeGraphRoute,
        startDestination = homeRoute
    ) {
        homeScreen()
    }
}

fun NavHostController.navigateToHomeGraph() {
    navigate(homeGraphRoute)
}