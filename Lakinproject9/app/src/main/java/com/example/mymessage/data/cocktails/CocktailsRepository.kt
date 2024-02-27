package com.example.mymessage.data.cocktails

import com.example.cocktails.data.models.Drink
import com.example.cocktails.domain.Cocktail

interface CocktailsRepository {

    suspend fun searchCocktails(text: String): List<Cocktail>

    suspend fun searchCocktailsByName(text: String): List<Cocktail>

    suspend fun getCocktailById(text: String): Drink?

}