package org.nibienvenu.fleetmanager


import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.nibienvenu.fleetmanager.presantation.screens.auth.LoginScreen
import org.nibienvenu.fleetmanager.presantation.viewmodels.LoginViewModel
import org.nibienvenu.fleetmanager.ui.theme.FleetManagementTheme


@Composable
@Preview
fun App() {
    FleetManagementTheme {
        var currentmenu = remember { mutableStateOf("") }


        LoginScreen(
            viewModel = LoginViewModel(),
            onLoginSuccess = {
                currentmenu.value = "home"
            },
            onNavigateToRegister = {
            },
            onNavigateToForgotPassword = {
            }
        )

    }
}