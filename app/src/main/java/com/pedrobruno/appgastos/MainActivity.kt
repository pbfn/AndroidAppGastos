package com.pedrobruno.appgastos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.pedrobruno.appgastos.ui.navigation.authGraph
import com.pedrobruno.appgastos.ui.navigation.authGraphRoute
import com.pedrobruno.appgastos.ui.navigation.homeGraph
import com.pedrobruno.appgastos.ui.navigation.navigateToAuthGraph
import com.pedrobruno.appgastos.ui.navigation.navigateToHomeGraph
import com.pedrobruno.appgastos.ui.navigation.navigateToSignIn
import com.pedrobruno.appgastos.ui.navigation.navigateToSignUp
import com.pedrobruno.appgastos.ui.theme.AppGastosTheme
import com.pedrobruno.appgastos.ui.viewmodels.AppState
import com.pedrobruno.appgastos.ui.viewmodels.AppViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGastosTheme {
                val navController = rememberNavController()
                val appViewModel = koinViewModel<AppViewModel>()
                val appState by appViewModel.state.collectAsState(initial = AppState())
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentParentRoute = currentBackStack?.destination?.parent?.route

                LaunchedEffect(appState) {
                    if (appState.isInitLoading) {
                        return@LaunchedEffect
                    }

                    appState.user?.let {
                        navController.navigateToHomeGraph(navOptions = navOptions {
                            currentParentRoute?.let { parentRoute ->
                                popUpTo(parentRoute) {
                                    inclusive = true
                                }
                            } ?: popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        })
                    } ?: navController.navigateToAuthGraph(navOptions = navOptions {
                        currentParentRoute?.let { parentRoute ->
                            popUpTo(parentRoute) {
                                inclusive = true
                            }
                        } ?: popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    })

                }
                NavHost(
                    navController = navController,
                    startDestination = "splashScreen"
                ) {
                    composable("splashScreen") {
                        //TODO alterar
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    authGraph(
                        onNavigateToSignIn = {
                            navController.navigateToSignIn()
                        },
                        onNavigateToSignUp = {
                            navController.navigateToSignUp()
                        }
                    )
                    homeGraph()
                }
            }
        }
    }
}
