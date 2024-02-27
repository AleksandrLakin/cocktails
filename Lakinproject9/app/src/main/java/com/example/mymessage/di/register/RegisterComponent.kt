package com.example.mymessage.di.register

import com.example.mymessage.ui.register.RegisterViewModel
import dagger.Subcomponent

@Subcomponent
interface RegisterComponent {

    fun viewModel(): RegisterViewModel

}