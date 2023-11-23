package com.example.rmc_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rmc_app.R
import com.example.rmc_app.app.RmcScreen
import com.example.rmc_app.components.ButtonComponent
import com.example.rmc_app.components.ClickableLoginTextComponent
import com.example.rmc_app.components.DividerTextComponent
import com.example.rmc_app.components.LargeHeadingTextComponent
import com.example.rmc_app.components.MyTextFieldComponent
import com.example.rmc_app.components.PasswordTextFieldComponent
import com.example.rmc_app.components.UnderLinedTextComponent
import com.example.rmc_app.data.login.LoginUIEvent
import com.example.rmc_app.data.login.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LargeHeadingTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                icon = Icons.Default.Email,
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                Modifier.fillMaxWidth()
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                icon = Icons.Default.Lock,
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
            )

            Spacer(modifier = Modifier.height(40.dp))

            UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

            ButtonComponent(
                value = stringResource(id = R.string.login),
                onButtonClicked = {
                    loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(
                tryingToLogin = false,
                onTextSelected = {
                    navController.navigate(RmcScreen.Register.name)
                }
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}

