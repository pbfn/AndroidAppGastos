package com.pedrobruno.appgastos.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pedrobruno.appgastos.ui.screens.SignUpScreen
import com.pedrobruno.appgastos.ui.viewmodels.SignUpViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

const val signUpRoute = "signUp"

fun NavGraphBuilder.signUpScreen(
    onNavigateToSignIn: () -> Unit
) {
    composable(signUpRoute) {
        val viewModel = koinViewModel<SignUpViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        val signUpIsSuccessful by viewModel.signUpIsSuccessful.collectAsState(false)
        val context = LocalContext.current
        LaunchedEffect(signUpIsSuccessful) {
            if (signUpIsSuccessful) {
                Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                onNavigateToSignIn()
            }
        }
        SignUpScreen(
            uiState = uiState,
            onSignUpClick = {
                scope.launch { viewModel.signUp() }
            })
    }
}

fun NavHostController.navigateToSignUp() {
    navigate(signUpRoute)
}