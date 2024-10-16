package com.pedrobruno.appgastos.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.pedrobruno.appgastos.authentication.FirebaseAuthRepository
import com.pedrobruno.appgastos.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine


data class AppState(
    val user: User? = null,
    val isInitLoading:Boolean = true
)

class AppViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AppState())
    val state = _state.combine(firebaseAuthRepository.currentUser) { appState, authResult ->
        val user = authResult.currentUser?.email?.let { User(it) }
        appState.copy(user = user, isInitLoading = authResult.isInitLoading)
    }

}