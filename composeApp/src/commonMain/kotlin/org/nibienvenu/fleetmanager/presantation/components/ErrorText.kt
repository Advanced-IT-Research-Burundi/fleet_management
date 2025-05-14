package org.nibienvenu.fleetmanager.presantation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.nibienvenu.fleetmanager.ui.theme.TerraCotta

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        color = TerraCotta,
        fontSize = 12.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 4.dp)
    )
}