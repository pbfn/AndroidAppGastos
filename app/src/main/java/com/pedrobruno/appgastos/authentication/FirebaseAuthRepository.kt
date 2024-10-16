package com.pedrobruno.appgastos.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(
    private val firebaseAuth: FirebaseAuth
) {

    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener {
            _currentUser.value = it.currentUser
        }
    }

    suspend fun createUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .await()
    }

    suspend fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .await()
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}