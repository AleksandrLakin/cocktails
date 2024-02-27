package com.example.mymessage.di.cocktails

import com.example.cocktails.presentation.main.MainViewModel
import dagger.Subcomponent

@Subcomponent
interface CocktailComponent {

    fun viewModel(): MainViewModel

}