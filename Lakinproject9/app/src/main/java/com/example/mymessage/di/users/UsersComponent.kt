package com.example.mymessage.di.users

import com.example.mymessage.ui.users.UsersViewModel
import dagger.Subcomponent

@Subcomponent
interface UsersComponent {

    fun viewModel(): UsersViewModel

}