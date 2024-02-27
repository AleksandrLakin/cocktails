package com.example.mymessage.data.cocktails

import com.example.cocktails.data.CocktailsAPI
import com.example.cocktails.data.models.Drink
import com.example.cocktails.domain.Cocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CocktailsRepositoryImpl @Inject constructor(
    private val api: CocktailsAPI
) : CocktailsRepository {
    override suspend fun searchCocktails(text: String): List<Cocktail> {
        return withContext(Dispatchers.IO) {
            api.searchCocktails(text).drinks?.map { it.asDomain() } ?: emptyList()
        }
    }

    override suspend fun searchCocktailsByName(text: String): List<Cocktail> {
        return withContext(Dispatchers.IO) {
            api.searchCocktailsByName(text).drinks?.map { it.asDomain() } ?: emptyList()
        }
    }

    override suspend fun getCocktailById(text: String): Drink? {
        return withContext(Dispatchers.IO) {
            api.getCocktailById(text).drinks?.first()
        }
    }
}