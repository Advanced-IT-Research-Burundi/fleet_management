package org.nibienvenu.fleetmanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val Sage50 = Color(0xFFF2F5F0)
val Sage100 = Color(0xFFE4EBE1)
val Sage200 = Color(0xFFCFDACA)
val Sage300 = Color(0xFFB6C9AF)
val Sage400 = Color(0xFF9EB896)
val Sage500 = Color(0xFF88A67E)
val Sage600 = Color(0xFF6D8B65)
val Sage700 = Color(0xFF57714E)
val Sage800 = Color(0xFF45583D)
val Sage900 = Color(0xFF303F2B)
val Sage950 = Color(0xFF1D261A)


val GreenAccent = Color(0xFF4CAF50)     // Actions positives
val AmberAccent = Color(0xFFFFC107)     // Notifications, avertissements
val TerraCotta = Color(0xFFE57373)      // Erreurs, alertes
val DeepTeal = Color(0xFF00796B)        // Actions secondaires
val PaleBackground = Color(0xFFF8FAF6)  // Fond d'écran principal


val Available = Color(0xFF4CAF50)        // Véhicules disponibles
val InUse = Color(0xFFFFA000)            // Véhicules en utilisation
val Maintenance = Color(0xFFE57373)      // Véhicules en maintenance
val Reserved = Color(0xFF5C6BC0)         // Véhicules réservés
val BackgroundLight = Color(0xFFF5F7F4)  // Fond pour les cartes et sections
val TitleColor = Color(0xFF3E5641)       // Couleur pour les titres
val TimeStatusColor = Color(0xFF687864)  // Pour les indicateurs de temps

private val LightColorPalette = lightColors(
    primary = Sage600,
    primaryVariant = Sage800,
    secondary = DeepTeal,
    background = PaleBackground,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF1C1C1C),
    onSurface = Color(0xFF1C1C1C)
)

private val DarkColorPalette = darkColors(
    primary = Sage400,
    primaryVariant = Sage600,
    secondary = Color(0xFF80CBC4),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun FleetManagementTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        content = content
    )
}