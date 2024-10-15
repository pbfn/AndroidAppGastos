package com.pedrobruno.appgastos.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pedrobruno.appgastos.ui.screens.SignInScreen
import com.pedrobruno.appgastos.ui.viewmodels.SignInViewModel
import org.koin.androidx.compose.koinViewModel

const val signInRoute = "signIn"

fun NavGraphBuilder.signInScreen(
    onNavigateToSignUp: () -> Unit,
) {
    composable(signInRoute) {
        val viewModel = koinViewModel<SignInViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        SignInScreen(
            uiState = uiState,
            onSignInClick = {},
            onSignUpClick = onNavigateToSignUp
        )
    }
}

fun NavHostController.navigateToSignIn() {
    navigate(signInRoute)
}

