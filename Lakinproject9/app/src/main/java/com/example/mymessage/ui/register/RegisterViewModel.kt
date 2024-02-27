package com.example.mymessage.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessage.data.auth.AuthRepository
import com.example.mymessage.domain.RegisterData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val state = MutableStateFlow(State())
    fun observeState() = state.asStateFlow()

    fun register(login: String, password: String, firstName: String = "", lastName: String = "") {
        val model = RegisterData(
            username = login,
            password = password,
            lastName = lastName,
            firstName = firstName
        )
        state.update { it.copy(inProgress = true) }
        viewModelScope.launch {
            if (repository.register(model)) {
                state.update { it.copy(success = true, error = null) }
            } else {
                state.update { it.copy(success = false, error = "Что-то пошло не так") }
            }
            state.update { it.copy(inProgress = false) }
        }
    }

}

data class State(
    val error: String? = null,
    val inProgress: Boolean = false,
    val success: Boolean = false
)