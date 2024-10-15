package com.pedrobruno.appgastos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.pedrobruno.appgastos.ui.navigation.authGraph
import com.pedrobruno.appgastos.ui.navigation.authGraphRoute
import com.pedrobruno.appgastos.ui.navigation.navigateToSignIn
import com.pedrobruno.appgastos.ui.navigation.navigateToSignUp
import com.pedrobruno.appgastos.ui.theme.AppGastosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGastosTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = authGraphRoute
                ) {
                    authGraph(
                        onNavigateToSignIn = {
                            navController.navigateToSignIn()
                        },
                        onNavigateToSignUp = {
                            navController.navigateToSignUp()
                        }
                    )
                }
            }
        }
    }
}
