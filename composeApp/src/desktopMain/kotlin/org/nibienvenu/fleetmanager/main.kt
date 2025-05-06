package org.nibienvenu.fleetmanager

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Fleet_manager",
    ) {
        App()
    }
}