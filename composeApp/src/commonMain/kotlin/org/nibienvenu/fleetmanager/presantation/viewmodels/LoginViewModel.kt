package org.nibienvenu.fleetmanager.presantation.viewmodels



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isPasswordVisible by mutableStateOf(false)
        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var loginSuccessful by mutableStateOf(false)
        private set

    var loginError by mutableStateOf<String?>(null)
        private set


    var rememberMe by mutableStateOf(false)
        private set


    fun updateEmail(input: String) {
        email = input
//        validateEmail()
    }

    fun updatePassword(input: String) {
        password = input
//        validatePassword()
    }

    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }

    fun toggleRememberMe() {
        rememberMe = !rememberMe
    }


    private fun validateEmail(): Boolean {
        return if (email.isEmpty()) {
            emailError = "L'email ne peut pas être vide"
            false
        } else if (!isValidEmail(email)) {
            emailError = "Format d'email invalide"
            false
        } else {
            emailError = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        return if (password.isEmpty()) {
            passwordError = "Le mot de passe ne peut pas être vide"
            false
        } else if (password.length < 6) {
            passwordError = "Le mot de passe doit contenir au moins 6 caractères"
            false
        } else {
            passwordError = null
            true
        }
    }


    fun login() {
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()

        if (!isEmailValid || !isPasswordValid) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                loginError = null

                // Simuler un appel réseau
                delay(1500)

                // Dans une application réelle, vous remplaceriez ceci par votre logique d'authentification
                // par exemple: authRepository.login(email, password)

                // Simulation d'une connexion réussie (à remplacer par la vérification réelle)
                if (email == "test@example.com" && password == "password123") {
                    loginSuccessful = true
                } else {
                    loginError = "Email ou mot de passe incorrect"
                }
            } catch (e: Exception) {
                loginError = e.message ?: "Une erreur est survenue lors de la connexion"
            } finally {
                isLoading = false
            }
        }
    }

    fun resetLoginState() {
        loginSuccessful = false
        loginError = null
    }
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")
        return emailRegex.matches(email)
    }

}