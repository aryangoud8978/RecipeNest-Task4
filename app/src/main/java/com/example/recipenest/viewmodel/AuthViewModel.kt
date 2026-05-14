package com.example.recipenest.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var isLoading = mutableStateOf(false)
        private set

    var authError = mutableStateOf<String?>(null)
        private set

    var isUserLoggedIn = mutableStateOf(
        auth.currentUser != null
    )
        private set

    fun loginUser(
        email: String,
        password: String
    ) {

        if (email.isBlank() || password.isBlank()) {

            authError.value = "Please fill all fields"
            return
        }

        isLoading.value = true

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                isLoading.value = false

                if (task.isSuccessful) {

                    isUserLoggedIn.value = true
                    authError.value = null

                } else {

                    authError.value =
                        task.exception?.message ?: "Login failed"
                }
            }
    }

    fun signupUser(
        email: String,
        password: String
    ) {

        if (email.isBlank() || password.isBlank()) {

            authError.value = "Please fill all fields"
            return
        }

        if (password.length < 6) {

            authError.value =
                "Password must be at least 6 characters"

            return
        }

        isLoading.value = true

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                isLoading.value = false

                if (task.isSuccessful) {

                    isUserLoggedIn.value = true
                    authError.value = null

                } else {

                    authError.value =
                        task.exception?.message ?: "Signup failed"
                }
            }
    }

    fun logoutUser() {

        auth.signOut()

        isUserLoggedIn.value = false
    }
}