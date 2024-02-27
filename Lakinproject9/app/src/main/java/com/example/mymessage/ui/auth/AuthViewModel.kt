package com.example.mymessage.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessage.data.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val state = MutableStateFlow(State())
    fun observeState() = state.asStateFlow()

    fun logIn(login: String, password: String) {
        state.update { it.copy(inProgress = true) }
        viewModelScope.launch {
            if (repository.login(login, password)) {
                state.update { it.copy(login = true, error = null) }
            } else {
                state.update { it.copy(login = false, error = "Что-то пошло не так") }
            }
            state.update { it.copy(inProgress = false) }
        }
    }

}

data class State(
    val login: Boolean = false,
    val error: String? = null,
    val inProgress: Boolean = false
)