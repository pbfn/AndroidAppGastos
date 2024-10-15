package com.pedrobruno.appgastos.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pedrobruno.appgastos.authentication.FirebaseAuthRepository
import com.pedrobruno.appgastos.ui.viewmodels.SignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
}

val repositoryModule = module {
    single { FirebaseAuthRepository(get()) }
}

val firebaseModule = module {
    single {
        Firebase.auth
    }
}