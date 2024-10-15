package com.pedrobruno.appgastos.ui.states

data class SignInUIState(
    val email: String = "",
    val password: String = "",
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val error: String? = null
)
