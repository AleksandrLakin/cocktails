package com.example.mymessage.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mymessage.R
import com.example.mymessage.ui.NavigationDestination

@Composable
fun AuthScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: AuthViewModel
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state = viewModel.observeState().collectAsState()

    if (state.value.login) {
        navController.navigate(NavigationDestination.UsersScreen.destination)
    }

    Column (
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(300.dp),
            value = login,
            onValueChange = { login = it },
            label = { Text(stringResource(R.string.login)) }
        )

        OutlinedTextField(
            modifier = Modifier
                .width(300.dp),
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(stringResource(R.string.password)) }
        )

        state.value.error?.let {
            Text(text = it)
        }

        Button(
            modifier = Modifier
                .width(300.dp)
                .padding(top = 16.dp),
            onClick = { viewModel.logIn(login = login, password = password) }
        ) {
            Text(text = stringResource(R.string.log_in))
        }

        FilledTonalButton(
            modifier = Modifier
                .width(300.dp),
            onClick = {
                navController.navigate(NavigationDestination.RegisterScreen.destination)
            }
        ) {
            Text(text = stringResource(R.string.create_an_account))
        }

        if (state.value.inProgress) {
            CircularProgressIndicator()
        }
    }
}