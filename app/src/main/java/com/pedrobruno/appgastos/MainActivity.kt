package com.pedrobruno.appgastos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.pedrobruno.appgastos.ui.screens.SignUpScreen
import com.pedrobruno.appgastos.ui.theme.AppGastosTheme
import com.pedrobruno.appgastos.ui.viewmodels.SignUpViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = koinViewModel<SignUpViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            val scope = rememberCoroutineScope()
            val signUpIsSuccessful by viewModel.signUpIsSuccessful.collectAsState(false)
            AppGastosTheme {
                val context = LocalContext.current
                LaunchedEffect(signUpIsSuccessful) {
                    if (signUpIsSuccessful) {
                        Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                    }
                }
                SignUpScreen(
                    uiState = uiState,
                    onSignUpClick = {
                        scope.launch { viewModel.signUp() }
                    })
            }
        }
    }
}
