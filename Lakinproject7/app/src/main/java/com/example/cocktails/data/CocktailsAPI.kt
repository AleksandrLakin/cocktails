package com.example.cocktails.data

import com.example.cocktails.data.models.CocktailsRemote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CocktailsAPI {

    @GET("api/json/v1/1/search.php")
    fun searchCocktails(@Query("f") text: String): Call<CocktailsRemote>

    @GET("api/json/v1/1/search.php")
    fun searchCocktailsByName(@Query("s") text: String): Call<CocktailsRemote>

    @GET("api/json/v1/1/lookup.php")
    fun getCocktailById(@Query("i") id: String): Call<CocktailsRemote>

}