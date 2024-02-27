package com.example.cocktails.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.domain.Cocktail
import com.example.mymessage.data.cocktails.CocktailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: CocktailsRepository
) : ViewModel() {

    private val state = MutableStateFlow(State())
    fun observeUi() = state.asStateFlow()

    fun search(text: String) {
        viewModelScope.launch {
            val cocktails = if (text.length == 1) {
                repository.searchCocktails(text)
            } else {
                repository.searchCocktailsByName(text)
            }

            state.update { it.copy(items = cocktails) }
        }
    }

}

data class State(
    val items: List<Cocktail> = emptyList(),
    val error: String? = null
)