package com.pedrobruno.appgastos.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pedrobruno.appgastos.ui.screens.SignInScreen
import com.pedrobruno.appgastos.ui.viewmodels.SignInViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

const val signInRoute = "signIn"

fun NavGraphBuilder.signInScreen(
    onNavigateToSignUp: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable(signInRoute) {
        val viewModel = koinViewModel<SignInViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        val isAuthenticated by viewModel.isAuthenticated.collectAsState(false)

        LaunchedEffect(isAuthenticated) {
            if(isAuthenticated){
                onNavigateToHome()
            }
        }

        SignInScreen(
            uiState = uiState,
            onSignInClick = {
                scope.launch {
                    viewModel.signIn()
                }
            },
            onSignUpClick = onNavigateToSignUp
        )
    }
}

fun NavHostController.navigateToSignIn() {
    navigate(signInRoute)
}

