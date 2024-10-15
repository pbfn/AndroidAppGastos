package com.pedrobruno.appgastos.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pedrobruno.appgastos.authentication.FirebaseAuthRepository
import com.pedrobruno.appgastos.ui.states.SignUpScreenUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpScreenUIState())
    val uiState = _uiState.asStateFlow()

    private val _signUpIsSuccessful = MutableSharedFlow<Boolean>()
    val signUpIsSuccessful = _signUpIsSuccessful.asSharedFlow()

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
                },
                onConfirmPasswordChange = { confirmPassword ->
                    _uiState.update {
                        it.copy(confirmPassword = confirmPassword)
                    }
                },
            )
        }
    }

    suspend fun signUp() {
        try {
            firebaseAuthRepository.createUser(
                email = _uiState.value.email,
                password = _uiState.value.email
            )
            _signUpIsSuccessful.emit(true)
        } catch (e: Exception) {
            Log.e("SignUpViewModel", "signUp: ", e)
            _uiState.update {
                it.copy(
                    error = e.message
                )
            }
            delay(3000)
            _uiState.update {
                it.copy(
                    error = null
                )
            }
        }
    }
}