package com.example.mymessage.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymessage.data.users.UsersRepository
import com.example.mymessage.domain.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val repository: UsersRepository
): ViewModel() {

    private val state = MutableStateFlow(State())
    fun observeUi() = state.asStateFlow()

    init {
        viewModelScope.launch {
            val users = repository.getUsers()
            state.update { it.copy(users = users) }
        }
    }

}

data class State(
    val users: List<User>  = emptyList()
)