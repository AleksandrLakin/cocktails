package com.example.mymessage.di.details

import com.example.cocktails.presentation.details.DetailsViewModel
import dagger.Subcomponent

@Subcomponent
interface DetailsComponent {
    fun viewModel(): DetailsViewModel
}