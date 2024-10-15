package com.pedrobruno.appgastos.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pedrobruno.appgastos.ui.states.SignUpScreenUIState
import com.pedrobruno.appgastos.ui.theme.AppGastosTheme
import com.pedrobruno.appgastos.ui.theme.Typography

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpScreenUIState,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Cadastrar Usuário",
            modifier = modifier.fillMaxWidth(),
            style = Typography.titleLarge
        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            label = { Text(text = "Email") },
            value = uiState.email,
            onValueChange = uiState.onEmailChange
        )
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            label = { Text(text = "Password") },
            value = uiState.password,
            onValueChange = uiState.onPasswordChange
        )
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            label = { Text(text = "Confirm Password") },
            value = uiState.confirmPassword,
            onValueChange = uiState.onConfirmPasswordChange
        )
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = { onSignUpClick() }) {
            Text(text = "Cadastrar", style = Typography.labelLarge)
        }
        AnimatedVisibility(visible = uiState.error != null) {
            uiState.error?.let {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true, name = "Default")
@Composable
private fun SignUpScreenPrev() {
    AppGastosTheme {
        SignUpScreen(uiState = SignUpScreenUIState(), onSignUpClick = {})
    }
}

@Preview(showSystemUi = true, name = "With Error")
@Composable
private fun SignUpScreen2Prev() {
    AppGastosTheme {
        SignUpScreen(uiState = SignUpScreenUIState(error = "Error"), onSignUpClick = {})
    }
}