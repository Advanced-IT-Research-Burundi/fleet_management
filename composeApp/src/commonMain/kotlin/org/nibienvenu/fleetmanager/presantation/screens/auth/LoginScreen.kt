package org.nibienvenu.fleetmanager.presantation.screens.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fleet_manager.composeapp.generated.resources.Res
import fleet_manager.composeapp.generated.resources.ic_error
import fleet_manager.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.nibienvenu.fleetmanager.presantation.components.CustomTextField
import org.nibienvenu.fleetmanager.presantation.components.ErrorText
import org.nibienvenu.fleetmanager.presantation.viewmodels.LoginViewModel
import org.nibienvenu.fleetmanager.ui.theme.DeepTeal
import org.nibienvenu.fleetmanager.ui.theme.PaleBackground
import org.nibienvenu.fleetmanager.ui.theme.Sage100
import org.nibienvenu.fleetmanager.ui.theme.Sage600
import org.nibienvenu.fleetmanager.ui.theme.Sage700
import org.nibienvenu.fleetmanager.ui.theme.Sage800
import org.nibienvenu.fleetmanager.ui.theme.TerraCotta
import org.nibienvenu.fleetmanager.ui.theme.TimeStatusColor

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val emailFocusRequester = remember { FocusRequester() }

    LaunchedEffect(viewModel.loginSuccessful) {
        if (viewModel.loginSuccessful) {
            onLoginSuccess()
            viewModel.resetLoginState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PaleBackground,
                        Sage100
                    )
                )
            )
    ) {
        // Fond décoratif en haut avec forme incurvée
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Sage700,
                            Sage600
                        )
                    )
                )
        ) {}

        // Contenu principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Logo amélioré avec effet d'ombre
            FleetManagerLogo()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Fleet Manager",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Sage800
            )

            Text(
                text = "Gérez votre flotte de véhicules efficacement",
                fontSize = 14.sp,
                color = TimeStatusColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Connexion",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepTeal,
                    modifier = Modifier.padding(bottom = 16.dp)
                )


                CustomTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.updateEmail(it) },
                    label = "Email",
                    leadingIcon = Icons.Filled.Email,
                    isError = viewModel.emailError != null,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(emailFocusRequester)
                )

                AnimatedVisibility(
                    visible = viewModel.emailError != null,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    viewModel.emailError?.let {
                        ErrorText(text = it)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))


                CustomTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.updatePassword(it) },
                    label = "Mot de passe",
                    leadingIcon = Icons.Filled.Lock,
                    isError = viewModel.passwordError != null,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            viewModel.login()
                        }
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true,
                    isPasswordVisible = viewModel.isPasswordVisible,
                    onTogglePasswordVisibility = { viewModel.togglePasswordVisibility() }
                )

                AnimatedVisibility(
                    visible = viewModel.passwordError != null,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    viewModel.passwordError?.let {
                        ErrorText(text = it)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Option "Se souvenir de moi" avec style amélioré
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = viewModel.rememberMe,
                            onCheckedChange = { viewModel.toggleRememberMe() },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Sage600,
                                uncheckedColor = TimeStatusColor
                            ),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Se souvenir de moi",
                            fontSize = 12.sp,
                            color = TimeStatusColor,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    // Lien "Mot de passe oublié" avec style amélioré
                    TextButton(
                        onClick = onNavigateToForgotPassword,
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Mot de passe oublié?",
                            fontSize = 12.sp,
                            color = DeepTeal,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bouton de connexion amélioré
            val color = if (viewModel.isLoading) Sage800 else Sage800
            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = color),
                enabled = !viewModel.isLoading
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Se connecter",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Message d'erreur de connexion amélioré
            AnimatedVisibility(
                visible = viewModel.loginError != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                viewModel.loginError?.let {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        backgroundColor = TerraCotta.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_error),
                                contentDescription = "Error Icon",
                                tint = TerraCotta,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = it,
                                color = TerraCotta,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Option pour créer un compte avec style amélioré
            Row(
                modifier = Modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Vous n'avez pas de compte?",
                    fontSize = 13.sp,
                    color = TimeStatusColor
                )
                TextButton(
                    onClick = onNavigateToRegister,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "S'inscrire",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepTeal
                    )
                }
            }
        }
    }
}

@Composable
fun FleetManagerLogo() {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color.White,
                        Color.White.copy(alpha = 0.9f)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Logo avec icône de voiture
        Image(
            painter = painterResource(Res.drawable.ic_logo),
            contentDescription = "Fleet Manager Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(48.dp)
        )
//        Icon(
//            imageVector = Icons.Filled.DirectionsCar,
//            contentDescription = "Fleet Manager Logo",
//            tint = DeepTeal,
//            modifier = Modifier.size(48.dp)
//        )
    }
}


