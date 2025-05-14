package org.nibienvenu.fleetmanager.presantation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.nibienvenu.fleetmanager.ui.theme.DeepTeal
import org.nibienvenu.fleetmanager.ui.theme.Sage600


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    isError: Boolean,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 14.sp) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "$label Icon",
                tint = Sage600,
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { onTogglePasswordVisibility?.invoke() }) {
                    VisibilityIcon(
                        visible = isPasswordVisible,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        } else null,
        visualTransformation = if (isPassword && !isPasswordVisible)
            PasswordVisualTransformation() else VisualTransformation.None,
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = modifier,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = DeepTeal,
            unfocusedBorderColor = Sage600.copy(alpha = 0.5f),
            cursorColor = DeepTeal,
            focusedLabelColor = DeepTeal
        ),
        shape = RoundedCornerShape(8.dp),
        textStyle = MaterialTheme.typography.body1.copy(fontSize = 14.sp)
    )
}