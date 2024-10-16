package com.pedrobruno.appgastos.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pedrobruno.appgastos.authentication.FirebaseAuthRepository
import com.pedrobruno.appgastos.ui.states.SignInUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class SignInViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUIState())
    val uiState = _uiState.asStateFlow()
    val isAuthenticated = firebaseAuthRepository.currentUser
        .map { it != null }

    init {
        _uiState.update { cureentState ->
            cureentState.copy(
                onEmailChange = { email ->
                    _uiState.update {
                        it.copy(email = email)
                    }
                },
                onPasswordChange = { password ->
                    _uiState.update {
                        it.copy(password = password)
                    }
                }
            )
        }
    }

    suspend fun signIn() {
        try {
            firebaseAuthRepository.signIn(
                email = uiState.value.email,
                password = uiState.value.password
            )
        } catch (e: Exception) {
            Log.e("SignInViewModel", "signIn: ", e)
            _uiState.update {
                it.copy(error = e.message)
            }
            delay(3000)
            _uiState.update {
                it.copy(error = null)
            }
        }
    }
}