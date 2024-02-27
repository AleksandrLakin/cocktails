package com.example.cocktails.data

import com.example.cocktails.data.models.CocktailsRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsAPI {

    @GET("api/json/v1/1/search.php")
    suspend fun searchCocktails(@Query("f") text: String): CocktailsRemote

    @GET("api/json/v1/1/search.php")
    suspend fun searchCocktailsByName(@Query("s") text: String): CocktailsRemote

    @GET("api/json/v1/1/lookup.php")
    suspend fun getCocktailById(@Query("i") id: String): CocktailsRemote

}