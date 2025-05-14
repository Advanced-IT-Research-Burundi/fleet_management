package org.nibienvenu.fleetmanager.presantation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun VisibilityIcon(
    visible: Boolean,
    modifier: Modifier = Modifier,
    eyeColor: Color = Color(0xFF6200EE), // Violet Material
    irisColor: Color = Color.White,
    strikeThroughLineColor: Color = Color.Red
) {
    Canvas(modifier = modifier.size(24.dp)) {
        val width = size.width
        val height = size.height
        val center = Offset(width / 2, height / 2)

        // Rayon principal de l'œil
        val eyeRadiusX = width * 0.4f
        val eyeRadiusY = height * 0.35f

        // Dessiner le contour de l'œil
        drawOval(
            color = eyeColor,
            size = Size(eyeRadiusX * 2, eyeRadiusY * 2),
            topLeft = Offset(center.x - eyeRadiusX, center.y - eyeRadiusY)
        )

        // Dessiner la pupille
        drawCircle(
            color = irisColor,
            radius = eyeRadiusX * 0.4f,
            center = center
        )

        // Dessiner la barre oblique si le mot de passe est masqué
        if (!visible) {
            drawLine(
                color = strikeThroughLineColor,
                start = Offset(eyeRadiusX * 0.4f, eyeRadiusY * 0.4f),
                end = Offset(width - eyeRadiusX * 0.4f, height - eyeRadiusY * 0.4f),
                strokeWidth = 2.5.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}