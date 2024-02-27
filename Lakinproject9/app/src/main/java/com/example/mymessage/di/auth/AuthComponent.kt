package com.example.mymessage.di.auth

import com.example.mymessage.ui.auth.AuthViewModel
import dagger.Subcomponent

@Subcomponent
interface AuthComponent {

    fun viewModel(): AuthViewModel

}