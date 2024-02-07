package com.example.cocktails.presentation.main

import androidx.lifecycle.ViewModel
import com.example.cocktails.data.CocktailsAPI
import com.example.cocktails.data.models.CocktailsRemote
import com.example.cocktails.domain.Cocktail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainViewModel(): ViewModel() {

    private val state = MutableStateFlow(State())
    fun observeUi() = state.asStateFlow()

    val api = Retrofit
        .Builder()
        .baseUrl("https://www.thecocktaildb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CocktailsAPI::class.java)

    fun search(text: String) {
        val response = if (text.length == 1) {
            try {
                api.searchCocktails(text)
            } catch (e: Exception) {
                state.update { it.copy(error = e.message) }
                return
            }
        } else {
            try {
                api.searchCocktailsByName(text)
            } catch (e: Exception) {
                state.update { it.copy(error = e.message) }
                return
            }

        }

        response.enqueue(object : Callback<CocktailsRemote> {
            override fun onResponse(call: Call<CocktailsRemote>, response: Response<CocktailsRemote>) {
                val body = response.body()
                body?.let {
                    val result = it.drinks?.map {
                        Cocktail(
                            id = it.idDrink,
                            name = it.strDrink,
                            src = it.strDrinkThumb,
                        )
                    } ?: emptyList()
                    state.update { it.copy(items = result, error = null) }
                }

            }

            override fun onFailure(call: Call<CocktailsRemote>, t: Throwable) {
                state.update { it.copy(error = t.message) }
            }

        })

    }

}

data class State(
    val items: List<Cocktail> = emptyList(),
    val error: String? = null
)