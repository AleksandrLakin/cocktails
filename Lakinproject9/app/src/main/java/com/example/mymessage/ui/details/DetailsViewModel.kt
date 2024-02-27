package com.example.cocktails.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.domain.Ingredient
import com.example.cocktails.presentation.toItems
import com.example.mymessage.data.cocktails.CocktailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val repository: CocktailsRepository
) : ViewModel() {

    private val state = MutableStateFlow(ViewState())
    fun observeUi() = state.asStateFlow()

    fun getData(cocktailId: String) {
        viewModelScope.launch {
            val item = repository.getCocktailById(cocktailId) ?: return@launch
            state.update {
                it.copy(
                    title = item.strDrink,
                    src = item.strDrinkThumb,
                    items = item.toItems()
                )
            }
        }
    }
}

data class ViewState(
    val title: String = "",
    val src: String? = null,
    val items: List<Ingredient> = emptyList()
)
